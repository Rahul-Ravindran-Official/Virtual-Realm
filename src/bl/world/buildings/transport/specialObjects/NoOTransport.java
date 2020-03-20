package bl.world.buildings.transport.specialObjects;

import bl.world.buildings.transport.modal.OTransport;
import bl.world.buildings.transport.modal.VehicleTypes;
import bl.world.buildings.transport.modal.vehicleSystems.OEngine;
import bl.world.buildings.transport.modal.vehicleSystems.OGasTank;
import bl.world.buildings.transport.modal.vehicleSystems.OSafetyModule;

public class NoOTransport {

    public static OTransport get(){
        return new OTransport(
                VehicleTypes.None,
                "No Vehicle Available",
                new OGasTank(0),
                new OEngine(0),
                new OSafetyModule(0,0,0),
                null
        );
    }

}
