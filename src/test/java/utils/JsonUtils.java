package utils;


import configs.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;


public class JsonUtils {

    /**
     * Method to Retrive value for particular key in json data
     *
     * @param key
     **/
    public String readFromJson(String key){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(Constants.dataPath+Constants.dataFileName));
            JSONObject jsonObject = (JSONObject)obj;
            return (String)jsonObject.get(key);
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
