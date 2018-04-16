package com.padmitriy.resultant;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDelegate;

import com.padmitriy.resultant.di.component.AppComponent;

/**
 * Created by dmitriy on 22.03.18.
 */

public class ResultantApp extends Application {

    public static AppComponent sAppComponent;
    @SuppressLint("StaticFieldLeak")

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = AppComponent.Initializer.init(this);
        sAppComponent.inject(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }
}
