package bl.world.buildings.restaurant;

import bl.world.buildings._commonUtilities.modals.OBuildingInfo;
import bl.world.buildings.restaurant.modals.ODisplayableRestaurant;
import bl.world.buildings.restaurant.modals.OConsumableOutput;

import java.util.ArrayList;

public interface IRestaurantMaster {
    public void init() throws Exception;
    public ArrayList<OBuildingInfo> getRestaurantsAround();
    public ODisplayableRestaurant enterRestaurant(int restaurantId);
    public OConsumableOutput consumeDishForEnergy(int menuPosition, String BankTransaction) throws Exception;
    public String getPayableName();
}
