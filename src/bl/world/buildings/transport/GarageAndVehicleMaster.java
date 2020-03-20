package bl.world.buildings.transport;

import bl.helper.InitializeCop;
import bl.helper.Objects.Coordinate;
import bl.world.buildings.transport.modal.OTransport;
import bl.world.buildings.transport.modal.VehicleTypes;
import bl.world.buildings.transport.modal.vehicleSystems.OEngine;
import bl.world.buildings.transport.modal.vehicleSystems.OGasTank;
import bl.world.buildings.transport.modal.vehicleSystems.OSafetyModule;
import bl.world.buildings.transport.specialObjects.NoOTransport;

import java.util.ArrayList;

public class GarageAndVehicleMaster extends InitializeCop {

    ArrayList<OTransport> vehicles = new ArrayList<>();
    private OTransport currentVehicle = null;

    private static GarageAndVehicleMaster ourInstance = new GarageAndVehicleMaster();
    public static GarageAndVehicleMaster getInstance() {
        return ourInstance;
    }
    private GarageAndVehicleMaster() {}

    public void init(ArrayList<OTransport> vehiclesOwned, OTransport currentVehicle){
        initialized();
    }

    public void addVehicleToGarage(OTransport vehicle) throws Exception {
        checkIfInitialised();
        vehicles.add(vehicle);
    }

    public boolean removeVehicleFromGarage(OTransport vehicle) throws Exception {
        checkIfInitialised();
        if(vehicles.contains(vehicle)){
            vehicles.remove(vehicle);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<OTransport> getVehiclesFromCoordinates(Coordinate coordinate) throws Exception {

        checkIfInitialised();
        ArrayList<OTransport> vehiclesFromACoordinate = new ArrayList<OTransport>();

        for (OTransport vehicle : vehicles) {
            if(
                    (vehicle.getCurrentCoordinate().getX() == coordinate.getX()) &&
                    (vehicle.getCurrentCoordinate().getY() == coordinate.getY())
            ){
                vehiclesFromACoordinate.add(vehicle);
            }
        }

        return vehiclesFromACoordinate;
    }

    public OTransport getCurrentVehicle(){
        if(currentVehicle != null){
            return currentVehicle;
        }else{
            return NoOTransport.get();
        }
    }

    public String getFuelOfCurrentVehicle(){
        return getCurrentVehicle().getGasTank().peekEntityAsString();
    }

    public String getFuelOfVehicle(OTransport transport){
        return transport.getGasTank().peekEntityAsString();
    }
}