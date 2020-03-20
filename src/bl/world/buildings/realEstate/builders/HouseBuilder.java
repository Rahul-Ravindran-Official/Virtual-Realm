package bl.world.buildings.realEstate.builders;

import bl.world.buildings.realEstate.modals.OHouse;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class HouseBuilder {

    public static HashMap<Integer, OHouse> build(ArrayList<JSONObject> houses){

        HashMap<Integer, OHouse> outputHouses = new HashMap<>();

        for (JSONObject house : houses) {
            OHouse bufferHouse = buildSingleHouse(house);
            outputHouses.put(bufferHouse.getHid(), bufferHouse);
        }

        return outputHouses;
    }

    private static OHouse buildSingleHouse(JSONObject house){

        int hid = Integer.parseInt((String) house.get("hid"));
        int houseOccupancyCount = Integer.parseInt((String) house.get("house_occupancy_count"));
        int unlockableId = Integer.parseInt((String) house.get("unlockable_id"));
        String house_name = (String) house.get("house_name");
        BigDecimal costOfRent = new BigDecimal((String) house.get("cost_of_rent"));
        BigDecimal costToPurchase = new BigDecimal((String) house.get("cost_to_purchase"));
        BigDecimal cashEarnedWhileRenting = new BigDecimal((String) house.get("cash_earned_while_renting"));
        BigDecimal cashEarnedWhenSold = new BigDecimal((String) house.get("cash_earned_when_sold"));

        OHouse bufferHouse = new OHouse();
        bufferHouse.setHid(hid);
        bufferHouse.setHouseOccupancyCount(houseOccupancyCount);
        bufferHouse.setUnlockableId(unlockableId);
        bufferHouse.setHouse_name(house_name);
        bufferHouse.setCostOfRent(costOfRent);
        bufferHouse.setCostToPurchase(costToPurchase);
        bufferHouse.setCashEarnedWhileRenting(cashEarnedWhileRenting);
        bufferHouse.setCashEarnedWhenSold(cashEarnedWhenSold);

        return bufferHouse;
    }

}
