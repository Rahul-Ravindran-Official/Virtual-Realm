package bl.helper;

import bl.helper.Objects.Api;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NxMethods {

    public static String getSourceCodeOfWebsite(Api api) {
        String fetched_data = "";
        try {
            URL url = new URL(api.getApi());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            StringBuilder response = new StringBuilder(50000);
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int i = 0;
            while ((i = bufferedReader.read()) > 0) {
                response.append((char) i);
            }
            fetched_data = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fetched_data;
    }

    public static ArrayList regularExpression(String patternBeginningEndAndRegex, String patternSource) {
        ArrayList<String> arrayListToAdd = new ArrayList<>();
        Pattern p = Pattern.compile(patternBeginningEndAndRegex);
        Matcher m = p.matcher(patternSource);

        while (m.find()) {
            arrayListToAdd.add(m.group(1));
        }

        return arrayListToAdd;
    }

    public static String regularExpressionLite(String patternBeginningEndAndRegex, String patternSource) {
        String returnableString = "";
        Pattern p = Pattern.compile(patternBeginningEndAndRegex);
        Matcher m = p.matcher(patternSource);

        while (m.find()) {
            returnableString = m.group(1);
        }

        return returnableString;
    }

    public static int getIndexOfIntInArrayList(ArrayList<Integer> arr, int toSearch){
        int index = -1;

        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i) == toSearch){
                return i;
            }
        }

        return index;
    }

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 32) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static ArrayList<JSONObject> getUsableJSONObject(String src) throws ParseException {
        ArrayList<JSONObject> returnableJsonObjects = new ArrayList<>();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(src.trim());
        // TODO: 2019-03-14 Dirty line -> need to simplify
        JSONArray jsonArray = (JSONArray) ((Map.Entry)((JSONObject)((Map.Entry)jsonObject.entrySet().toArray()[0]).getValue()).entrySet().toArray()[1]).getValue();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject singleObject = (JSONObject) jsonArray.get(i);
            returnableJsonObjects.add(singleObject);
        }

        return returnableJsonObjects;
    }

    public static ArrayList<HashMap<String,String>> getDataFromBackendlessJSON(Api api, String[] variablesToExtractDataFrom) throws ParseException {

        String src = NxMethods.getSourceCodeOfWebsite(api);
        ArrayList<JSONObject> jsonObjects = NxMethods.getUsableJSONObject(src);
        ArrayList<HashMap<String,String>> returnableValuesOfAllRecords = new ArrayList<>();

        for (JSONObject jsonObject : jsonObjects) {
            HashMap<String,String> valuesOfSingleRecord = new HashMap<>();
            for (String variable : variablesToExtractDataFrom) {
                String value = (String) jsonObject.get(variable);
                valuesOfSingleRecord.put(variable,value);
            }
            returnableValuesOfAllRecords.add(valuesOfSingleRecord);
        }

        return returnableValuesOfAllRecords;
    }

}
