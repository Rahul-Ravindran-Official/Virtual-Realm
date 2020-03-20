package bl.world.buildings.restaurant.modals;
import bl.annotations.Tested;
import bl.helper.Objects.Coordinate;
import bl.helper.Objects.OperatingTime;
import bl.world.buildings._commonUtilities.modals.OBuildingInfo;

import java.util.ArrayList;

/**
 * Object for storing parameters that define a restaurant
 */
@Tested
public class ORestaurant {
    private int restaurantId;
    private OBuildingInfo buildingInfo;
    private OMenu menu = new OMenu();
    private boolean tippable;
    private ArrayList<OWaiter> waiters = new ArrayList<>();
    private OperatingTime operatingTime;

    public ORestaurant(int restaurantId, OBuildingInfo buildingInfo, OMenu menu, boolean tippable, ArrayList<OWaiter> waiters, OperatingTime operatingTime) {
        this.restaurantId = restaurantId;
        this.buildingInfo = buildingInfo;
        this.menu = menu;
        this.tippable = tippable;
        this.waiters = waiters;
        this.operatingTime = operatingTime;
    }

    public ORestaurant() {
    }

    public void addWaiter(OWaiter waiter){
        this.waiters.add(waiter);
    }

    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    public OBuildingInfo getBuildingInfo() {
        return buildingInfo;
    }
    public void setBuildingInfo(OBuildingInfo buildingInfo) {
        this.buildingInfo = buildingInfo;
    }
    public OMenu getMenu() {
        return menu;
    }
    public void setMenu(OMenu menu) {
        this.menu = menu;
    }
    public boolean isTippable() {
        return tippable;
    }
    public void setTippable(boolean tippable) {
        this.tippable = tippable;
    }
    public ArrayList<OWaiter> getWaiters() {
        return waiters;
    }
    public void setWaiters(ArrayList<OWaiter> waiters) {
        this.waiters = waiters;
    }
    public OperatingTime getOperatingTime() {
        return operatingTime;
    }
    public void setOperatingTime(OperatingTime operatingTime) {
        this.operatingTime = operatingTime;
    }

}