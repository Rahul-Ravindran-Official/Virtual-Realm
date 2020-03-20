package bl.world.buildings.restaurant.builders;

import bl.annotations.Tested;
import bl.helper.Objects.Coordinate;
import bl.helper.Objects.OperatingTime;
import bl.world.buildings._commonUtilities.modals.OBuildingInfo;
import bl.world.buildings.restaurant.modals.ODisplayableRestaurant;
import bl.world.buildings.restaurant.modals.ORestaurant;
import bl.world.buildings.restaurant.modals.OWaiter;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RestaurantsBuilder {
    @Tested
    public static ArrayList<ORestaurant> build(ArrayList<JSONObject> restaurants){
        ArrayList<ORestaurant> partialRestaurants = new ArrayList<>();
        for (JSONObject restaurant : restaurants) {
            partialRestaurants.add(buildRestaurant(restaurant));
        }
        return partialRestaurants;
    }
    @Tested
    private static ORestaurant buildRestaurant(JSONObject singleRestaurant){

        String rid = (String) singleRestaurant.get("rid");
        String restaurant_name = (String) singleRestaurant.get("restaurant_name");
        String coordinate_x = (String) singleRestaurant.get("coordinate_x");
        String coordinate_y = (String) singleRestaurant.get("coordinate_y");
        String is_tippable = (String) singleRestaurant.get("is_tippable");
        String closing_time = (String) singleRestaurant.get("closing_time");
        String opening_time = (String) singleRestaurant.get("opening_time");
        String closed_message = (String) singleRestaurant.get("closed_message");

        boolean isTippable = is_tippable.equals("1");
        Coordinate coordinate = new Coordinate(Integer.parseInt(coordinate_x),Integer.parseInt(coordinate_y));
        OperatingTime operatingTime = new OperatingTime(
                Integer.parseInt(opening_time),
                Integer.parseInt(closing_time),
                closed_message
        );

        ORestaurant oRestaurant = new ORestaurant();
        oRestaurant.setTippable(isTippable);
        oRestaurant.setRestaurantId(Integer.parseInt(rid));
        oRestaurant.setBuildingInfo(new OBuildingInfo(restaurant_name,coordinate,Integer.parseInt(rid), new ORestaurant()));
        oRestaurant.setOperatingTime(operatingTime);

        return oRestaurant;
    }

    public static ODisplayableRestaurant buildDisplayableRestaurant(ORestaurant restaurant){
        ODisplayableRestaurant displayableRestaurant = new ODisplayableRestaurant();
        displayableRestaurant.setMenu(restaurant.getMenu());
        displayableRestaurant.setOperatingTime(restaurant.getOperatingTime());
        displayableRestaurant.setRestaurantName(restaurant.getBuildingInfo().getBuildingName());
        displayableRestaurant.setTippable(restaurant.isTippable());
        displayableRestaurant.setWaiter(getRandomWaiter(restaurant.getWaiters()));
        return displayableRestaurant;
    }

    private static OWaiter getRandomWaiter(ArrayList<OWaiter> waiter){
        int size = waiter.size();
        if(size == 0) {
            ArrayList<Integer> restaurantsIdsWorkFor = new ArrayList<>();
            return new OWaiter(-1, "Waiter Bot",66, true, 100, restaurantsIdsWorkFor);
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, size);
        return waiter.get(randomNum);
    }

}
