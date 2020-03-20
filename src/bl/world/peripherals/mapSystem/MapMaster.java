package bl.world.peripherals.mapSystem;

import bl.world.peripherals.mapSystem.modals.OMap;

public class MapMaster {

    private static MapMaster ourInstance = new MapMaster();
    public static MapMaster getInstance() {
        return ourInstance;
    }
    private MapMaster() {
    }

    // [y,x]
    private OMap map = new OMap();

    public void init(){

    }

}
