package bl.world.buildings.realEstate.slaves;

public class RealEstateManagerSlave {


    private static RealEstateManagerSlave ourInstance = new RealEstateManagerSlave();
    public static RealEstateManagerSlave getInstance() {
        return ourInstance;
    }
    private RealEstateManagerSlave() {}



}
