package bl.world.buildings.fuelStation;

import bl.person.systems.PurseSystem;
import bl.world.buildings.transport.modal.OTransport;

import java.math.BigDecimal;

public class FuelStationMaster {

    private static FuelStationMaster ourInstance = new FuelStationMaster();
    public static FuelStationMaster getInstance() {
        return ourInstance;
    }
    private FuelStationMaster() {}

    private double fuelCostPerUnit;

    public void init(double fuelCostPerUnit){
        this.fuelCostPerUnit = fuelCostPerUnit;
    }

    public boolean buyFuelForVehicle(int units, PurseSystem purseSystem, OTransport vehicle){

        double costToCharge = units * fuelCostPerUnit;
        boolean paid = purseSystem.withdraw(String.valueOf(costToCharge));
        if(paid){
            vehicle.addGas(units);
            return true;
        }
        return false;

    }

    public double getFuelCostPerUnit() {
        return fuelCostPerUnit;
    }

}