package bl.game;

import bl.helper.APIs;
import bl.helper.NxMethods;
import bl.helper.Objects.OConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

public class Config {

    HashMap<String, OConfig> configurations = new HashMap<>();

    private static Config ourInstance = new Config();
    public static Config getInstance() {
        return ourInstance;
    }
    private Config() {}


    public void init() throws ParseException {
        String src = NxMethods.getSourceCodeOfWebsite(APIs.getConfig);
        ArrayList<JSONObject> configs = NxMethods.getUsableJSONObject(src);
        for (JSONObject config : configs) {
            String configId = (String) config.get("cid");
            String configName = (String) config.get("config_name");
            String configValue = (String) config.get("config_value");
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
