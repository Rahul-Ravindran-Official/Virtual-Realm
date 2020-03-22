package bl.world.buildings.salon.initializers;

import bl.helper.APIs;
import bl.helper.NxMethods;
import bl.helper.Objects.OConfig;
import bl.world.buildings.salon.modals.OHaircutStyle;
import org.json.simple.parser.ParseException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class InitializeSalon {

    public static ArrayList<OHaircutStyle> initializeSalon() throws ParseException {
        ArrayList<OHaircutStyle> haircutStyles = new ArrayList<>();

        ArrayList<HashMap<String,String>> getDataFromBackendlessJSON = NxMethods.getDataFromBackendlessJSON(
                APIs.getSalonHairStyles,
                new String[] { "hid", "haircut_style_name", "attractiveness_boost", "cost_for_haircut", "base64_haircut_image" }
        );

        for (HashMap<String, String> record : getDataFromBackendlessJSON) {
            haircutStyles.add(buildHaircut(record));
        }

        return haircutStyles;
    }

    private static OHaircutStyle buildHaircut(HashMap<String, String> record){
        String hid = record.get("hid");
        String haircutStyleName = record.get("haircut_style_name");
        String attractiveness = record.get("attractiveness");
        String costForHaircut = record.get("cost_for_haircut");
        String base64HaircutImage = record.get("base64_haircut_image");
        OHaircutStyle haircutStyle = new OHaircutStyle();
        haircutStyle.setHairstyleId(Integer.parseInt(hid));
        haircutStyle.setCostForHaircut(new BigInteger(costForHaircut));
        haircutStyle.setHaircutStyleName(haircutStyleName);
        haircutStyle.setIncreaseInAttractiveness(Integer.parseInt(attractiveness));
        haircutStyle.setBase64HaircutImage(base64HaircutImage);
        return haircutStyle;
    }

}
