package bl.world.buildings.transport.modal;

import bl.helper.Objects.Coordinate;
import bl.world.buildings.transport.modal.vehicleSystems.OEngine;
import bl.world.buildings.transport.modal.vehicleSystems.OGasTank;
import bl.world.buildings.transport.modal.vehicleSystems.OSafetyModule;
import lombok.AllArgsConstructor;
import lombok.Data;

public class OTransport {

    VehicleTypes vehicleType;
    String vehicleName;
    OGasTank gasTank;
    OEngine engine;
    OSafetyModule safetyModule;
    Coordinate currentCoordinate;

    public OTransport(VehicleTypes vehicleType, String vehicleName, OGasTank gasTank, OEngine engine, OSafetyModule safetyModule, Coordinate currentCoordinate) {
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.gasTank = gasTank;
        this.engine = engine;
        this.safetyModule = safetyModule;
        this.currentCoordinate = currentCoordinate;

    }

    public OTransport() {}

    public void addGas(int gas){
        gasTank.gainEntity(gas);
    }

    public VehicleTypes getVehicleType() {
        return vehicleType;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public OGasTank getGasTank() {
        return gasTank;
    }

    public OEngine getEngine() {
        return engine;
    }

    public OSafetyModule getSafetyModule() {
        return safetyModule;
    }

    public Coordinate getCurrentCoordinate() {
        return currentCoordinate;
    }
}
