package bl.world.buildings.restaurant.builders;

import bl.annotations.Tested;
import bl.world.buildings.restaurant.modals.OMenu;
import bl.world.buildings.restaurant.modals.OMenuItem;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuBuilder {
    @Tested
    public static HashMap<Integer,OMenu> build(ArrayList<JSONObject> menuItems){
        HashMap<Integer,OMenu> restaurantIDMenus = new HashMap<>();
        ArrayList<OMenuItem> unorganisedMenuItems = new ArrayList<>();
        unorganisedMenuItems = getUnorganisedMenuItems(menuItems);

        for (OMenuItem unorganisedMenuItem : unorganisedMenuItems) {
            for (Integer restaurantID : unorganisedMenuItem.getRestaurantIdsWhereMade()) {
                if(restaurantIDMenus.containsKey(restaurantID)){
                    OMenu buffer = restaurantIDMenus.get(restaurantID);
                    buffer.addMenuItem(unorganisedMenuItem);
                    restaurantIDMenus.put(restaurantID,buffer);
                }else{
                    OMenu buffer = new OMenu();
                    buffer.setRestaurantId(restaurantID);
                    buffer.addMenuItem(unorganisedMenuItem);
                    restaurantIDMenus.put(restaurantID,buffer);
                }
            }
        }

        return restaurantIDMenus;
    }
    @Tested
    private static ArrayList<OMenuItem> getUnorganisedMenuItems(ArrayList<JSONObject> menuItems){
        ArrayList<OMenuItem> unorganisedMenuItems = new ArrayList<>();
        for (JSONObject menuItem : menuItems) {
            OMenuItem temp = menuItemBuilder(menuItem);
            unorganisedMenuItems.add(temp);
        }
        return unorganisedMenuItems;
    }
    @Tested
    private static OMenuItem menuItemBuilder(JSONObject menuItem){
        String item_name = (String) menuItem.get("item_name");
        String item_cost = (String) menuItem.get("item_cost");
        int availability = Integer.parseInt((String) menuItem.get("availability"));
        double probability_of_poisoning = Double.parseDouble((String) menuItem.get("probability_of_poisoning"));
        int hp_drain_from_poison = Integer.parseInt((String) menuItem.get("hp_drain_from_poison"));
        int menu_item_id = Integer.parseInt((String) menuItem.get("menu_item_id"));
        int restaurant_id = Integer.parseInt((String) menuItem.get("restaurant_id"));
        int energy_gain = Integer.parseInt((String) menuItem.get("energy_gain"));

        boolean isAvailable = (availability == 1);

        OMenuItem oMenuItem = new OMenuItem();
        oMenuItem.setItemName(item_name);
        oMenuItem.setProbabilityOfPoisoning(probability_of_poisoning);
        oMenuItem.setHpDrainFromPoison(hp_drain_from_poison);
        oMenuItem.setAvailability(isAvailable);
        oMenuItem.setItemCost(new BigDecimal(item_cost));
        oMenuItem.setItemID(menu_item_id);
        oMenuItem.setEnergyGain(energy_gain);
        oMenuItem.addToRestaurantIdsWhereMade(restaurant_id);

        return oMenuItem;
    }

}