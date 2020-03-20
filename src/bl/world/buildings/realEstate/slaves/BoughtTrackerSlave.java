package bl.world.buildings.realEstate.slaves;

import bl.person.systems.PurseSystem;
import bl.person.systems.UnlockableSystem;
import bl.world.buildings.realEstate.modals.OHouse;

import java.math.BigDecimal;
import java.util.HashMap;

public class BoughtTrackerSlave {

    HashMap<Integer, OHouse> housesBought = new HashMap<>();

    private static BoughtTrackerSlave ourInstance = new BoughtTrackerSlave();
    public static BoughtTrackerSlave getInstance() {
        return ourInstance;
    }
    private BoughtTrackerSlave() {}

    public BigDecimal costToBuyHouse(OHouse house){
        return house.getCostToPurchase();
    }
    public boolean buyHouse(OHouse house){
        BigDecimal costOfHouse = house.getCostToPurchase();
        boolean canAfford = PurseSystem.getInstance().canAffordCash(costOfHouse);
        boolean hasHouseAlready = housesBought.containsKey(house.getHid());

        if (!canAfford || !hasHouseAlready) {
            return false;
        }else{

            PurseSystem.getInstance().withdraw(costOfHouse.toString());
            housesBought.put(house.getHid(),house);
            UnlockableSystem.getInstance().addUnlockedItem(house.getUnlockableId());

        }
        return true;
    }
    public BigDecimal cashReturnOnSellingHouse(OHouse house){
        return house.getCashEarnedWhenSold();
    }
    public boolean sellHouse(OHouse house){
        BigDecimal costReceivedUponSelling = house.getCostToPurchase();
        boolean hasHouse = housesBought.containsKey(house.getHid());

        if (!hasHouse) {
            return false;
        }

        PurseSystem.getInstance().deposit(costReceivedUponSelling.toString());
        housesBought.remove(house.getHid());
        UnlockableSystem.getInstance().removeUnlockedItemIfExists(house.getUnlockableId());

        return true;
    }
    public HashMap<Integer, OHouse> getHousesBought(){
        return housesBought;
    }


}
