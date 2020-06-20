package com.example.bakingtutorialapp.Utils;

import android.util.Log;

import com.example.bakingtutorialapp.POJO.IngredientsPOJO;
import com.example.bakingtutorialapp.POJO.ReciepePOJO;
import com.example.bakingtutorialapp.POJO.StepsPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    private JsonUtils() {
    }

    private static final String LOG_ERROR = "jsonUtilClass";

    public static ArrayList<ReciepePOJO> fetchFeaturesFromJson(String json) {
        ArrayList<ReciepePOJO> reciepePOJOArrayList = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray baseArray = new JSONArray(json);
                for (int i = 0; i < baseArray.length(); i++) {
                    JSONObject baseObject = baseArray.getJSONObject(i);
                    JSONArray ingredientsArray = baseObject.getJSONArray("ingredients");
                    JSONArray stepsArray = baseObject.getJSONArray("steps");
                    ArrayList<IngredientsPOJO> ingredientsPOJOS = fetchIngredients(ingredientsArray);
                    ArrayList<StepsPOJO> stepsPOJOS = fetchSteps(stepsArray);
                    String recId = baseObject.getString("id");
                    String recName = baseObject.getString("name");
                    String recServings = baseObject.getString("servings");
                    ReciepePOJO newReciepePOJO = new ReciepePOJO(Integer.parseInt(recId),recName, stepsPOJOS, ingredientsPOJOS,recServings);
                    reciepePOJOArrayList.add(newReciepePOJO);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            Log.e(LOG_ERROR,"Json value passed is null");

        }
        return reciepePOJOArrayList;
    }

    private static ArrayList<IngredientsPOJO> fetchIngredients(JSONArray ingredientsJsonArray) {
        ArrayList<IngredientsPOJO> ingredientsArrayList = new ArrayList<>();
        try {
            for (int k = 0; k < ingredientsJsonArray.length(); k++) {
                JSONObject ingredientsJsonObject = ingredientsJsonArray.getJSONObject(k);
                String quantity = ingredientsJsonObject.getString("quantity");
                String measure = ingredientsJsonObject.getString("measure");
                String ingredient = ingredientsJsonObject.getString("ingredient");
                IngredientsPOJO ingredientsPOJO = new IngredientsPOJO(quantity, measure, ingredient);
                ingredientsArrayList.add(ingredientsPOJO);
            }
        } catch (JSONException e) {
            Log.e(LOG_ERROR, "Error fetchIngredientsFeatures -" + e);
        }
        return ingredientsArrayList;
    }


    private static ArrayList<StepsPOJO> fetchSteps(JSONArray stepsJsonArray) {
        ArrayList<StepsPOJO> stepsArrayList = new ArrayList<>();
        try {
            for (int k = 0; k < stepsJsonArray.length(); k++) {
                JSONObject stepsJsonObject = stepsJsonArray.getJSONObject(k);
                String id = stepsJsonObject.getString("id");
                String shortDescription = stepsJsonObject.getString("shortDescription");
                String description = stepsJsonObject.getString("description");
                String videoURL = stepsJsonObject.getString("videoURL");
                StepsPOJO stepsPOJO = new StepsPOJO(id, shortDescription, description,videoURL);
                stepsArrayList.add(stepsPOJO);
            }
        } catch (JSONException e) {
            Log.e(LOG_ERROR, "Error fetchIngredientsFeatures -" + e);
        }
        return stepsArrayList;
    }
}
