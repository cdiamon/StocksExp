package com.padmitriy.resultant.di.module;

import android.content.Context;

import com.padmitriy.resultant.ResultantApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class, ManagerModule.class})
public class AppModule {
    private ResultantApp app;

    public AppModule(ResultantApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app.getApplicationContext();
    }
}