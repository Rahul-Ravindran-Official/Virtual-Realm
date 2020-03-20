package bl.app.sharedComponents;

import bl.app.LogicAppBinder;
import bl.person.Person;
import bl.world.World;
import java.util.HashMap;

public class Header {

    /**     Required Variables:
     *      Name
     *      Age
     *      Cash In Purse
     *      Cash In Bank
     *      Gas Remaining
     */
    public static HashMap<String, Object> getData() throws Exception {


        HashMap<String, Object> output = new HashMap<>();

        Person person = LogicAppBinder.getInstance().getPerson();
        World world = LogicAppBinder.getInstance().getWorld();

        String name = person.getName();
        String cashInPurse = person.purseSystem.peekCashAsString();
        String age = person.getDisplayAge();
        String cashInBank = world.bankMaster.peekSavings();
        String gasRemaining = world.garageAndVehicleMaster.getFuelOfCurrentVehicle();

        output.put("name", name);
        output.put("cashInPurse", cashInPurse);
        output.put("age", age);
        output.put("cashInBank", cashInBank);
        output.put("gasRemaining", gasRemaining);

        return output;
    }

}