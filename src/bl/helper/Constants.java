package bl.helper;

import bl.helper.Objects.OConfig;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {

    HashMap<String, OConfig> configurations = new HashMap<>();

    public void init() throws ParseException {

        HashMap<String, OConfig> configurations = new HashMap<>();


        ArrayList<HashMap<String,String>> getDataFromBackendlessJSON = NxMethods.getDataFromBackendlessJSON(
                APIs.getConfig,
                new String[] { "cid", "config_name", "config_value" }
        );

        for (HashMap<String, String> record : getDataFromBackendlessJSON) {
            String configId = record.get("cid");
            String configName = record.get("config_name");
            String configValue = record.get("config_value");
            OConfig configuration = new OConfig(
                    Integer.parseInt(configId),
                    configName,
                    configValue
            );
            configurations.put(configName,configuration);
        }



    }

    public OConfig getConfiguration(String config){
        return configurations.get(config);
    }

}