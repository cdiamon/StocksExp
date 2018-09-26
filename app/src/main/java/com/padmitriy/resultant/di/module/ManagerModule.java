package com.padmitriy.resultant.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.padmitriy.resultant.util.PreferencesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Provides
    @Singleton
    PreferencesManager providePrefManager(Context context, Gson gson) {
        return new PreferencesManager(context, gson);
    }
}
