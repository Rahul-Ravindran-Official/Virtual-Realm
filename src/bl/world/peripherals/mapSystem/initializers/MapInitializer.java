package bl.world.peripherals.mapSystem.initializers;

import bl.helper.APIs;
import bl.helper.NxMethods;
import bl.helper.Objects.OConfig;
import bl.world.peripherals.mapSystem.modals.OMap;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

public class MapInitializer {

    public static OMap initializeMap() throws ParseException {
        OMap map = getMapFromServer();
        return map;
    }

    public static OMap getMapFromServer() throws ParseException {

        OMap returnableMap = new OMap();
        ArrayList<HashMap<String,String>> getDataFromBackendlessJSON = NxMethods.getDataFromBackendlessJSON(
                APIs.getMap,
                new String[] { "mid", "", "config_value" }
        );
        // TODO: 2019-03-15 Endpoint ... Do this aspect sometime.

        return null;

    }

}
