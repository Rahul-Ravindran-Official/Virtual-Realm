package bl.world.buildings.restaurant;

import bl.helper.InitializeCop;
import bl.person.systems.PurseSystem;
import bl.person.systems.abstractSystems.EnergySystem;
import bl.person.systems.abstractSystems.LuckSystem;
import bl.world.buildings._commonUtilities.modals.OBuildingInfo;
import bl.world.buildings._commonUtilities.moduleProtocol.Module;
import bl.world.buildings.bank.BankMaster;
import bl.world.buildings.restaurant.builders.RestaurantsBuilder;
import bl.world.buildings.restaurant.modals.ODisplayableRestaurant;
import bl.world.buildings.restaurant.modals.OConsumableOutput;
import bl.world.buildings.restaurant.modals.ORestaurant;
import bl.world.buildings.restaurant.initializers.InitializeRestaurants;
import bl.world.buildings.restaurant.modals.OWaiter;
import bl.world.buildings.restaurant.slaves.RestaurantMasterSlave;
import bl.world.peripherals.time.Time;

import java.util.ArrayList;

public class RestaurantMaster extends InitializeCop implements Module {

    private ArrayList<ORestaurant> restaurants = new ArrayList<>();
    private static int currentRestaurantEnteredId;
    private static ORestaurant currentRestaurant;
    private static OWaiter currentWaiter;
    private static String payableName = "Restaurant_INC";

    ///////////////////////////////////////////////////////////////////////////
    public static RestaurantMaster getInstance() {
        return ourInstance;
    }
    private RestaurantMaster() {}
    private static RestaurantMaster ourInstance = new RestaurantMaster();
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void init(Object... args) throws Exception {
        restaurants = InitializeRestaurants.getInstance().getRestaurantsForInitialization();
        initialized();
    }


    public ArrayList<OBuildingInfo> getRestaurantsAround() throws Exception {
        checkIfInitialised();
        return RestaurantMasterSlave.getRestaurantsForBrowsingFromArrayListORestaurants(restaurants);
    }
    public ODisplayableRestaurant enterRestaurant(int restaurantId) throws Exception {
        checkIfInitialised();
        currentRestaurantEnteredId = restaurantId;
        currentRestaurant = RestaurantMasterSlave.getRestaurantByRestaurantID(currentRestaurantEnteredId, restaurants);
        ODisplayableRestaurant displayableRestaurant = RestaurantsBuilder.buildDisplayableRestaurant(currentRestaurant);
        currentWaiter = displayableRestaurant.getWaiter();
        return displayableRestaurant;
    }
    public OConsumableOutput consumeDishForEnergy(int menuPosition, String bankTransactionId, EnergySystem energySystem) throws Exception {
        checkIfInitialised();
        OConsumableOutput oConsumableOutput = RestaurantMasterSlave.getDeliverableByMenuPosition(menuPosition,currentRestaurant.getMenu());
        boolean paid = BankMaster.getInstance().verifyBankTransaction(bankTransactionId,oConsumableOutput.getItemCost());
        Time.getInstance().incrementTime(2);
        if(paid){
            energySystem.gainEntity(oConsumableOutput.getEnergy());
            return oConsumableOutput;
        }else {
            return null;
        }
    }
    public boolean exitRestaurant(){
        currentRestaurantEnteredId = 0;
        currentRestaurant = null;
        return true;
    }

    /**
     * @return luck as int is returned so that the frontend can show how much luck has been added. Luck has already been added.
     */
    public int tipWaiter(PurseSystem purseSystem, BankMaster bankMaster, LuckSystem luckSystem, float percentageOfCashTo) throws Exception {
        return RestaurantMasterSlave.tipWaiter(purseSystem,bankMaster,luckSystem,percentageOfCashTo, currentWaiter);
    }

    // Helper
    public String getPayableName() throws Exception {
        checkIfInitialised();
        return payableName;
    }

}