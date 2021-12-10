package com.example.hangman;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要先传入一个json作为初始化
 * 然后只需要get...返回需要的 list
 */
public class JSONProcessor {


    public static final String TAG = "JSON Processor";
    private JSONObject first = null;
    private JSONObject metaObj = null;
    private String result;

    // 记录API只返回一个Array的情况
    private ArrayList<String> badCondition=new ArrayList<String>();
    private TextView tv;

    /**
     * constructor
     *
     * @param result: result String form getResultJson
     */
    JSONProcessor(String result) {
        this.result = result;
        try {
            String newResult = "{\"dict\":" + result + "}";
            Log.d(TAG, "json is" + newResult);
            JSONObject json = new JSONObject(newResult);
            JSONArray resultArray = json.getJSONArray("dict");
            this.badCondition=fromArrayToList(resultArray);
            this.first = resultArray.getJSONObject(0);
            this.metaObj = first.getJSONObject("meta");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * get list of string when bad condition
     *
     * return list of string
     */
    public ArrayList<String> getBadCondition(){
        return badCondition;
    }

    /**
     * @return 近义词 ArrayList<String>
     */
    public ArrayList<String> getSyns() {
        //get syns
        if (metaObj==null){
            return null;
        }
        JSONArray syns = null;
        try {
            syns = metaObj.getJSONArray("syns").getJSONArray(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> syns_list = fromArrayToList(syns);
        Log.d(TAG, "FIRST ITEM IS " + syns_list.get(1));
        return syns_list;
    }

    /**
     * @return 反义词 ArrayList
     */
    public ArrayList<String> getAnts() {
        if (metaObj==null){
            return null;
        }
        //get ants
        JSONArray ants = null;
        try {
            ants = metaObj.getJSONArray("ants").getJSONArray(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<String> ants_list = fromArrayToList(ants);
        Log.d(TAG, "ANTI IS " + ants_list.get(0));
        return ants_list;
    }

    /**
     * @param
     * @return Boolean mean offensive if true, false otherwise
     */
    public Boolean getOffensive() {
        if (metaObj==null){
            return null;
        }
        boolean offensive = false;
        try {
            offensive = metaObj.getBoolean("offensive");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Boolean is " + offensive);
        return offensive;
    }

    /**
     * get phrase
     *
     * @return
     */
    public ArrayList<String> getPhrase() {
        if (metaObj==null){
            return null;
        }
        //get short phrase
        JSONArray phrase = null;
        try {
            phrase = first.getJSONArray("shortdef");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> phrase_list = fromArrayToList(phrase);

        Log.d(TAG, "short phrase is" + phrase_list.get(0));

        return phrase_list;
    }


    /**
     * form Array TO List
     * helper function
     *
     * @param jsonArray
     * @return
     */
    public ArrayList<String> fromArrayToList(JSONArray jsonArray) {

        if(jsonArray==null){
            return null;
        }
        ArrayList<String> rt = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                try {
                    rt.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return rt;
    }

}
