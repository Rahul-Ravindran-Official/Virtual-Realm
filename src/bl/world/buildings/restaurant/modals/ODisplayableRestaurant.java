package bl.world.buildings.restaurant.modals;

import bl.helper.Objects.OperatingTime;
import bl.world.buildings._commonUtilities.modals.OBuildingInfo;

/**
 * This object will be returned for those who enter a restaurant
 */
public class ODisplayableRestaurant {
    private String restaurantName;
    private OMenu menu;
    private OWaiter waiter;
    private boolean tippable;
    private OperatingTime operatingTime;

    public ODisplayableRestaurant(String restaurantName, OMenu menu, OWaiter waiter, boolean tippable, OperatingTime operatingTime) {
        this.restaurantName = restaurantName;
        this.menu = menu;
        this.waiter = waiter;
        this.tippable = tippable;
        this.operatingTime = operatingTime;
    }
    public ODisplayableRestaurant() {
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public OMenu getMenu() {
        return menu;
    }
    public void setMenu(OMenu menu) {
        this.menu = menu;
    }
    public OWaiter getWaiter() {
        return waiter;
    }
    public void setWaiter(OWaiter waiter) {
        this.waiter = waiter;
    }
    public boolean isTippable() {
        return tippable;
    }
    public void setTippable(boolean tippable) {
        this.tippable = tippable;
    }
    public OperatingTime getOperatingTime() {
        return operatingTime;
    }
    public void setOperatingTime(OperatingTime operatingTime) {
        this.operatingTime = operatingTime;
    }
}
