package bl.world.buildings.restaurant.slaves;

import bl.person.systems.PurseSystem;
import bl.person.systems.abstractSystems.LuckSystem;
import bl.world.buildings._commonUtilities.modals.OBuildingInfo;
import bl.world.buildings.bank.BankMaster;
import bl.world.buildings.restaurant.modals.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RestaurantMasterSlave {

    public static ORestaurant getRestaurantByRestaurantID(int restaurantID, ArrayList<ORestaurant> restaurants){
        for (ORestaurant restaurant : restaurants) {
            if(restaurant.getRestaurantId() == restaurantID){
                return restaurant;
            }
        }
        return null;
    }
    public static OConsumableOutput getDeliverableByMenuPosition(int menuPosition, OMenu menu){
        OMenuItem menuItem = menu.getMenuItems().get(menuPosition);
        boolean isPoisoned = getBinaryOutputForBinaryProbability(menuItem.getProbabilityOfPoisoning());
        int poisonHpDrain = (isPoisoned) ? menuItem.getHpDrainFromPoison() : 0;
        OConsumableOutput oConsumableOutput = new OConsumableOutput();
        oConsumableOutput.setEnergy(menuItem.getEnergyGain());
        oConsumableOutput.setPoisoned(isPoisoned);
        oConsumableOutput.setHpDrainedFromPoison(poisonHpDrain);
        oConsumableOutput.setItemCost(menuItem.getItemCost());
        oConsumableOutput.setItemName(menuItem.getItemName());
        return oConsumableOutput;
    }

    /**
     * Note this method works with max precision of 0.01
     */
    private static boolean getBinaryOutputForBinaryProbability(double probability){
        int getRandomNumber = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
        int getUpgradedNumber = Integer.parseInt(String.valueOf(Math.round(probability*100)));
        if(getRandomNumber <= getUpgradedNumber){
            return true;
        }
        return false;
    }
    public static ArrayList<OBuildingInfo> getRestaurantsForBrowsingFromArrayListORestaurants(ArrayList<ORestaurant> restaurants){
        ArrayList<OBuildingInfo> restaurantsForBrowsing = new ArrayList<>();
        for (ORestaurant restaurant : restaurants) {
            restaurantsForBrowsing.add(restaurant.getBuildingInfo());
        }
        return restaurantsForBrowsing;
    }

    // Tip Waiter
    public static BigDecimal cashConsumedWhenTipping(PurseSystem purseSystem, BankMaster bankMaster, float percentageOfCashTo){
        return getCashFromAPercentageOfTotalNetWorth(purseSystem,bankMaster,percentageOfCashTo);
    }
    public static int tipWaiter(PurseSystem purseSystem, BankMaster bankMaster,LuckSystem luckSystem, float percentageOfCashTo, OWaiter waiter) throws Exception {
        BigDecimal cashToCharge = getCashFromAPercentageOfTotalNetWorth(purseSystem,bankMaster,percentageOfCashTo);
        chargeCustomerPurseAndBank(cashToCharge, purseSystem, bankMaster);
        int luckGained = Math.round(waiter.getLuck() * percentageOfCashTo);
        luckSystem.gainEntity(luckGained);
        return luckGained;
    }
    private static BigDecimal getCashFromAPercentageOfTotalNetWorth(PurseSystem purseSystem, BankMaster bankMaster, float percentage){
        BigDecimal totalCash = purseSystem.peekCashAsBigDecimal().add(purseSystem.peekCashAsBigDecimal());
        BigDecimal cashToCharge = totalCash.multiply(new BigDecimal(percentage));
        return cashToCharge;
    }
    private static void chargeCustomerPurseAndBank(BigDecimal cashToCharge, PurseSystem purseSystem, BankMaster bankMaster) throws Exception {
        BigDecimal cashInPurse = purseSystem.peekCashAsBigDecimal();
        if(cashInPurse.compareTo(cashToCharge) >= 0){
            purseSystem.withdraw(cashToCharge.toString());
        }else{
            BigDecimal cashToWithdrawFromBank = cashToCharge.subtract(purseSystem.peekCashAsBigDecimal());
            bankMaster.withdrawFromSavings(cashToWithdrawFromBank);
            purseSystem.depleteAllCash();
        }
    }

}