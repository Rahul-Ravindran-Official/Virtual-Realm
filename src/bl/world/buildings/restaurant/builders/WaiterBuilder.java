package bl.world.buildings.restaurant.builders;

import bl.annotations.Tested;
import bl.helper.NxMethods;
import bl.world.buildings.restaurant.modals.OWaiter;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class WaiterBuilder {

    @Tested
    public static ArrayList<OWaiter> build(ArrayList<JSONObject> waiters){
        ArrayList<OWaiter> allWaiters = new ArrayList<>();
        ArrayList<Integer> waiterIds = new ArrayList<>();
        for (JSONObject waiter : waiters) {
            OWaiter temp = buildWaiter(waiter);
            int locationOfDuplicateWaiter = NxMethods.getIndexOfIntInArrayList(waiterIds,temp.getWaiterId());
            if(-1 == locationOfDuplicateWaiter){
                allWaiters.add(buildWaiter(waiter));
                waiterIds.add(temp.getWaiterId());
            }else{
                OWaiter buffer = allWaiters.get(locationOfDuplicateWaiter);
                buffer.addRestaurantIWorkForToList(temp.getOneRestaurantID());
                allWaiters.set(locationOfDuplicateWaiter,buffer);
            }
        }
        return allWaiters;
    }
    @Tested
    private static OWaiter buildWaiter(JSONObject singleWaiter){

        int waiter_id = Integer.parseInt((String) singleWaiter.get("waiter_id"));
        String name = (String) singleWaiter.get("name");
        int age = Integer.parseInt((String) singleWaiter.get("age"));
        int tippable = Integer.parseInt((String) singleWaiter.get("tippable"));
        int luck = Integer.parseInt((String) singleWaiter.get("luck"));
        int restaurant_id = Integer.parseInt((String) singleWaiter.get("restaurant_id"));

        boolean isTippable = (tippable == 1);

        OWaiter oWaiter = new OWaiter();
        oWaiter.setAge(age);
        oWaiter.setName(name);
        oWaiter.setLuck(luck);
        oWaiter.setTippable(isTippable);
        oWaiter.setWaiterId(waiter_id);
        oWaiter.addRestaurantIWorkForToList(restaurant_id);

        return oWaiter;
    }


}
