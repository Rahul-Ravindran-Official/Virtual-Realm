package bl.world.buildings.restaurant.initializers;

import bl.annotations.Tested;
import bl.helper.APIs;
import bl.helper.NxMethods;
import bl.world.buildings.restaurant.modals.OMenu;
import bl.world.buildings.restaurant.modals.ORestaurant;
import bl.world.buildings.restaurant.modals.OWaiter;
import bl.world.buildings.restaurant.builders.MenuBuilder;
import bl.world.buildings.restaurant.builders.RestaurantsBuilder;
import bl.world.buildings.restaurant.builders.WaiterBuilder;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitializeRestaurants {

    ///////////////////////////////////////////////////////////////////////////
    public static InitializeRestaurants getInstance() {
        return ourInstance;
    }
    private InitializeRestaurants() {}
    private static InitializeRestaurants ourInstance = new InitializeRestaurants();
    ///////////////////////////////////////////////////////////////////////////

    /**
     * This method helps Restaurant Master to initialized its restaurants
     */
    @Tested
    public ArrayList<ORestaurant> getRestaurantsForInitialization() throws Exception {
        return getFinalRestaurants();
    }



    @Tested
    public ArrayList<ORestaurant> getFinalRestaurants() throws Exception {
        ArrayList<ORestaurant> restaurantsWithoutWaitersAndMenu = getRestaurants();
        ArrayList<OWaiter> completeWaiters = getWaiters();
        HashMap<Integer,OMenu> completeMenus = getMenus();
        ArrayList<ORestaurant> restaurantsWithoutMenu = mergeRestaurantsAndWaiters(restaurantsWithoutWaitersAndMenu, completeWaiters);
        ArrayList<ORestaurant> restaurantsWithMenuAndWaiters = mergeRestaurantsAndMenu(restaurantsWithoutMenu,completeMenus);

        return restaurantsWithMenuAndWaiters;
    }

    // Helper Functions for method => getFinalRestaurants()
    @Tested
    private String getRestaurantsFromServer(){
        return NxMethods.getSourceCodeOfWebsite(APIs.getRestaurants);
    }
    @Tested
    private String getWaitersFromServer(){
        return NxMethods.getSourceCodeOfWebsite(APIs.getWaiters);
    }
    @Tested
    private String getMenuItemsFromServer(){
        return NxMethods.getSourceCodeOfWebsite(APIs.getMenuItems);
    }
    @Tested
    private ArrayList<ORestaurant> getRestaurants() throws Exception {
        String src = getRestaurantsFromServer();
        ArrayList<JSONObject> restaurantAsJSONObject = NxMethods.getUsableJSONObject(src);
        ArrayList<ORestaurant> partialRestaurants = RestaurantsBuilder.build(restaurantAsJSONObject);
        return partialRestaurants;
    }
    @Tested
    private ArrayList<OWaiter> getWaiters() throws Exception {
        String src = getWaitersFromServer();
        ArrayList<JSONObject> waitersAsJSONObject = NxMethods.getUsableJSONObject(src);
        ArrayList<OWaiter> allWaiters = WaiterBuilder.build(waitersAsJSONObject);
        return allWaiters;
    }
    @Tested
    private HashMap<Integer,OMenu> getMenus() throws Exception {
        String src = getMenuItemsFromServer();
        ArrayList<JSONObject> menuItemsAsJSONObject = NxMethods.getUsableJSONObject(src);
        HashMap<Integer,OMenu> allMenu = MenuBuilder.build(menuItemsAsJSONObject);
        return allMenu;
    }

    private ArrayList<ORestaurant> mergeRestaurantsAndWaiters(ArrayList<ORestaurant> partialRestaurants, ArrayList<OWaiter> completeWaiters){
        for (OWaiter waiter : completeWaiters) {
            for (Integer restaurantID : waiter.getRestaurantsIdsWorkFor()) {
                for (int i = 0; i < partialRestaurants.size(); i++) {
                    if (partialRestaurants.get(i).getRestaurantId() == restaurantID){
                        ORestaurant restaurantToAddWaiter = partialRestaurants.get(i);
                        restaurantToAddWaiter.addWaiter(waiter);
                        partialRestaurants.set(i,restaurantToAddWaiter);
                    }
                }
            }
        }
        // it's still a partial restaurant; cause menu items haven't been added
        return partialRestaurants;
    }
    private ArrayList<ORestaurant> mergeRestaurantsAndMenu(ArrayList<ORestaurant> partialRestaurants, HashMap<Integer, OMenu> restaurantIDMenus){
        for (Map.Entry<Integer, OMenu> menu : restaurantIDMenus.entrySet()) {
            int key = menu.getKey();
            OMenu bufferMenu =  menu.getValue();
            for (int i = 0; i < partialRestaurants.size(); i++) {
                if(partialRestaurants.get(i).getRestaurantId() == key){
                    ORestaurant bufferRestaurant = partialRestaurants.get(i);
                    bufferRestaurant.setMenu(bufferMenu);
                    partialRestaurants.set(i,bufferRestaurant);
                }
            }
        }
        // Note : it's not a partial restaurant anymore :)
        return partialRestaurants;
    }

}