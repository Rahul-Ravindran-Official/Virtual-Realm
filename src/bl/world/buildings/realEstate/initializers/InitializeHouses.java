package bl.world.buildings.realEstate.initializers;

import bl.helper.APIs;
import bl.helper.NxMethods;
import bl.world.buildings.realEstate.builders.HouseBuilder;
import bl.world.buildings.realEstate.modals.OHouse;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

public class InitializeHouses {


    private static InitializeHouses ourInstance = new InitializeHouses();
    public static InitializeHouses getInstance() {
        return ourInstance;
    }
    private InitializeHouses() {}

    public HashMap<Integer, OHouse> getHousesForInitialization() throws ParseException {
        HashMap<Integer,OHouse> houses = getHouses();
        return houses;
    }

    private HashMap<Integer,OHouse> getHouses() throws ParseException {
        String src = getHousesFromServer();
        ArrayList<JSONObject> housesAsJSONObject = NxMethods.getUsableJSONObject(src);
        HashMap<Integer,OHouse> houses = HouseBuilder.build(housesAsJSONObject);
        return houses;
    }



    // Rest API Linkage Here
    private String getHousesFromServer(){
        return NxMethods.getSourceCodeOfWebsite(APIs.getHouses);
    }


}
