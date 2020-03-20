package bl.world.buildings.realEstate;

import bl.helper.InitializeCop;
import bl.helper.exceptions.ArgumentMismatchException;
import bl.world.buildings._commonUtilities.moduleProtocol.Module;
import bl.world.buildings.realEstate.helper.objects.HouseId;
import bl.world.buildings.realEstate.initializers.InitializeHouses;
import bl.world.buildings.realEstate.modals.ODisplayableHouse;
import bl.world.buildings.realEstate.modals.OHouse;
import bl.world.buildings.realEstate.modals.OTrackRent;
import bl.world.buildings.realEstate.slaves.BoughtTrackerSlave;
import bl.world.buildings.realEstate.slaves.RentTrackerSlave;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RealEstateMaster extends InitializeCop implements Module {

    private HashMap<Integer, OHouse> houses = new HashMap<>();
    private String payableName = "Real-Estate_INC";
    private RentTrackerSlave rentTrackerSlave;

    private static RealEstateMaster ourInstance = new RealEstateMaster();
    public static RealEstateMaster getInstance() {
        return ourInstance;
    }
    private RealEstateMaster() {}


    @Override
    /**
     * @param -> HashMap <Integer, OTrackRent> trackRents
     */
    public void init(Object... args) throws Exception {

        HashMap<Integer, OTrackRent> trackRents = new HashMap<>();

        try {
             trackRents = (HashMap<Integer, OTrackRent>) args[0];
        }catch (Exception E){
            System.out.println("Argument Mismatch Exception");
            // TODO: 2019-05-11 verify if this works
        }

        houses = InitializeHouses.getInstance().getHousesForInitialization();
        rentTrackerSlave = RentTrackerSlave.getInstance();
        rentTrackerSlave.init(trackRents);

        initialized();
    }

    public ArrayList<ODisplayableHouse> getAllRealEstates(){

        ArrayList<ODisplayableHouse> output = new ArrayList<>();
        HashMap<Integer,ODisplayableHouse> bufferOutputHouses = new HashMap<>();
        HashMap<Integer,OHouse> housesBought = BoughtTrackerSlave.getInstance().getHousesBought();

        // Adding the template values to outputHouses
        for (Map.Entry<Integer,OHouse> entry : houses.entrySet()){
            OHouse house = entry.getValue();
            ODisplayableHouse displayableHouse = new ODisplayableHouse();
            displayableHouse.setHid(house.getHid());
            displayableHouse.setCostOfRent(house.getCostOfRent());
            displayableHouse.setCostToPurchase(house.getCostToPurchase());
            displayableHouse.setHouse_name(house.getHouse_name());
            displayableHouse.setHouseOccupancyCount(house.getHouseOccupancyCount());
            bufferOutputHouses.put(house.getHid(), displayableHouse);
        }

        // Adding Bought Values
        for (Map.Entry<Integer,ODisplayableHouse> entry : bufferOutputHouses.entrySet()){
            int hid = entry.getKey();
            ODisplayableHouse displayableHouse = entry.getValue();
            boolean isHouseBought = housesBought.containsKey(hid);
            displayableHouse.setBought(isHouseBought);
            bufferOutputHouses.put(hid, displayableHouse);
        }

        // Setting all daysLeft as 0
        for (Map.Entry<Integer,ODisplayableHouse> entry : bufferOutputHouses.entrySet()){
            ODisplayableHouse house = entry.getValue();
            house.setHoursLeftOfOccupancy(new BigInteger("0"));
            bufferOutputHouses.put(house.getHid(), house);
        }


        // Adding daysLeftValues
        HashMap<Integer,OTrackRent> rents = RentTrackerSlave.getInstance().getTrackRents();
        for (Map.Entry<Integer,OTrackRent> entry : rents.entrySet()){
            int hid = entry.getValue().getHouseId();
            int hoursLeft = entry.getValue().getHoursLeft();

            if (bufferOutputHouses.containsKey(hid)) {
                ODisplayableHouse odh = bufferOutputHouses.get(hid);
                odh.setHoursLeftOfOccupancy(new BigInteger(String.valueOf(hoursLeft)));
                bufferOutputHouses.put(hid,odh);
            }
        }

        // Converting Map to ArrayList for ease in frontend side.
        for (Map.Entry<Integer,ODisplayableHouse> entry : bufferOutputHouses.entrySet()) {
            output.add(entry.getValue());
        }

        return output;
    }
    public boolean buyRealEstate(int houseId){
        return BoughtTrackerSlave.getInstance().buyHouse(houses.get(houseId));
    }
    public boolean rentRealEstate(int houseID, int days) throws Exception {
        OHouse house = houses.get(houseID);
        RentTrackerSlave.getInstance().rentRealEstate(house.getCostOfRent(),house,days);
        return true;
    }




    // Helper
    public String getPayableName() {
        return payableName;
    }
    public int getRentalTimeRemainingOnRentedRealEstate(int hid){
        return rentTrackerSlave.getRentalTimeRemainingOnRentedRealEstate(hid);
    }
    public String costOfRealEstateToRent(int houseID, int days){
        OHouse house = houses.get(houseID);
        return rentTrackerSlave.costOfRealEstateToRent(house.getCostOfRent(),house,days);
    }

}