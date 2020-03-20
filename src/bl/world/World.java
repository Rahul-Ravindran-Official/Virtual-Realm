package bl.world;

import bl.game.Config;
import bl.world.buildings._commonUtilities.moduleProtocol.Module;
import bl.world.buildings.bank.BankMaster;
import bl.world.buildings.fuelStation.FuelStationMaster;
import bl.world.buildings.jobMarket.JobMarketMaster;
import bl.world.buildings.realEstate.RealEstateMaster;
import bl.world.buildings.restaurant.RestaurantMaster;
import bl.world.buildings.salon.SalonMaster;
import bl.world.buildings.transport.GarageAndVehicleMaster;
import bl.world.buildings.transport.modal.OTransport;
import bl.world.peripherals.time.Time;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class World{

    ArrayList<Module> modules = new ArrayList<>();
    
    public Time time = Time.getInstance();
    public RestaurantMaster restaurantMaster = RestaurantMaster.getInstance();
    public BankMaster bankMaster = BankMaster.getInstance();
    public RealEstateMaster realEstateMaster = RealEstateMaster.getInstance();
    public JobMarketMaster jobMarketMaster = JobMarketMaster.getInstance();
    public FuelStationMaster fuelStationMaster = FuelStationMaster.getInstance();
    public SalonMaster salonMaster = SalonMaster.getInstance();
    public GarageAndVehicleMaster garageAndVehicleMaster = GarageAndVehicleMaster.getInstance();

    ///////////////////////////////////////////////////////////////////////////
    private static World ourInstance = new World();
    public static World getInstance() {
        return ourInstance;
    }
    private World() {
    }
    ///////////////////////////////////////////////////////////////////////////

    public void init(
            int timePassedInHours,
            int timeInHoursOfInterestLastPaid,
            BigDecimal cashInSavingsAccount
    ) throws Exception {

        time.init(timePassedInHours);
        restaurantMaster.init();
        realEstateMaster.init(
                new HashMap<>()
        );
        jobMarketMaster.init(
                new HashMap<>(),
                null
        );
        bankMaster.init(
                timeInHoursOfInterestLastPaid,
                cashInSavingsAccount,
                new HashMap()
        );

        fuelStationMaster.init(
                Config.getInstance()
                      .getConfiguration("fuelCostPerUnit")
                      .getConfigValueAsDouble()
        );

        garageAndVehicleMaster.init(
                new ArrayList<OTransport>(),
                null
        );

        // Create API before re-enabling
        // salonMaster.init();


    }

}
