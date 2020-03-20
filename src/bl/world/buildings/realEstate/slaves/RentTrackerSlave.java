package bl.world.buildings.realEstate.slaves;

import bl.person.systems.PurseSystem;
import bl.world.buildings.realEstate.modals.OHouse;
import bl.world.buildings.realEstate.modals.OTrackRent;
import bl.world.peripherals.time.ITimeSubscriber;
import bl.world.peripherals.time.Time;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RentTrackerSlave implements ITimeSubscriber {

    // < UnlockableID , OTrackRent >
    public HashMap <Integer, OTrackRent> trackRents = new HashMap<>();

    private static RentTrackerSlave ourInstance = new RentTrackerSlave();
    public static RentTrackerSlave getInstance() {
        return ourInstance;
    }
    private RentTrackerSlave() {}

    public void init(HashMap <Integer, OTrackRent> trackRents) throws Exception {
        Time.getInstance().subscribeForTime(this);
        this.trackRents = trackRents;
    }

    public boolean rentRealEstate(BigDecimal costToRent, OHouse house, int days) throws Exception {

        // Check if can be afforded
        BigDecimal cash = new BigDecimal(String.valueOf(costToRent.multiply(new BigDecimal(String.valueOf(days)))));
        boolean canAfford = PurseSystem.getInstance().canAffordCash(cash);
        if (canAfford) {
            PurseSystem.getInstance().withdraw(cash.toString());
        }else {
            return false;
        }

        // Add rent
        int hid = house.getHid();
        int unlockableId = house.getUnlockableId();
        boolean containsKey = trackRents.containsKey(unlockableId);
        if(containsKey){
            OTrackRent trackRent = trackRents.get(unlockableId);
            trackRent.addHoursLeft(days*24);
            trackRents.put(unlockableId,trackRent);
            trackRent.setToTimePassedInHours( trackRent.getToTimePassedInHours() + days*24 );
        }else {
            int currentTimePassedInHours = Time.getInstance().getTimePassedInHours();
            int hoursToAdd = days*24;
            OTrackRent oTrackRent = new OTrackRent();
            oTrackRent.setHoursLeft(hoursToAdd);
            oTrackRent.setHouseId(hid);
            oTrackRent.setUnlockableId(unlockableId);
            oTrackRent.setFromTimePassedInHours(currentTimePassedInHours);
            oTrackRent.setToTimePassedInHours(currentTimePassedInHours + hoursToAdd);
            trackRents.put(oTrackRent.getUnlockableId(),oTrackRent);
        }
        return true;
    }


    @Override
    public void receiveTimeOnChange(int timePassedInHours) {
        updateRentalHours(timePassedInHours);
    }

    private void updateRentalHours(int timePassedInHours){
        for (Map.Entry<Integer, OTrackRent> entry : trackRents.entrySet()){
            int key = entry.getKey();
            OTrackRent value = entry.getValue();
            int hoursLeft = value.getToTimePassedInHours() - timePassedInHours;
            value.setHoursLeft(hoursLeft);
            trackRents.put(key,value);
            if(hoursLeft <= 0){
                trackRents.remove(key);
            }
        }
    }

    public HashMap<Integer, OTrackRent> getTrackRents() {
        return trackRents;
    }


    // For Testing
    public int getRentalTimeRemainingOnRentedRealEstate(int hid){
        for (Map.Entry<Integer, OTrackRent> entry : trackRents.entrySet()) {
            if(entry.getValue().getHouseId() == hid){
                return entry.getValue().getHoursLeft();
            }
        }
        return -1;
    }
    public String costOfRealEstateToRent(BigDecimal costToRent, OHouse house, int days){
        return String.valueOf(costToRent.multiply(new BigDecimal(String.valueOf(days))));
    }
}