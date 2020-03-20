package bl.world.buildings.restaurant.modals;

import bl.annotations.Tested;

import java.util.ArrayList;

@Tested
public class OMenu {
    private ArrayList<OMenuItem> menuItems = new ArrayList<>();
    private int restaurantId;

    public OMenu(ArrayList<OMenuItem> menuItems, int restaurantId) {
        this.menuItems = menuItems;
        this.restaurantId = restaurantId;
    }
    public OMenu() {
    }

    public void addMenuItem(OMenuItem menuItem){
        this.menuItems.add(menuItem);
    }
    public ArrayList<OMenuItem> getMenuItems() {
        return menuItems;
    }
    public void setMenuItems(ArrayList<OMenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

}
