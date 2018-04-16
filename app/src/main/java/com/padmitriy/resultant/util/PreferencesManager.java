package com.padmitriy.resultant.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class PreferencesManager {

    private static final String BASE_URL = "BASE_URL";
    private static final String BASE_URL_PROD = "http://phisix-api3.appspot.com/";
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public PreferencesManager(Context context, Gson gson) {
        this.sharedPreferences = context.getSharedPreferences("Main", Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public String getBaseUrl() {
        return sharedPreferences.getString(BASE_URL, BASE_URL_PROD);
    }
}
