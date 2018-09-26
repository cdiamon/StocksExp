package com.padmitriy.resultant.view.activity.welcome;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.padmitriy.resultant.mvvm.ViewModel;
import com.padmitriy.resultant.view.activity.main.MainActivity;

public class WelcomeViewModel extends ViewModel {

    public WelcomeViewModel(@Nullable State savedInstanceState) {
        super(savedInstanceState);
    }

    public void onLoadingTimerFinished(Activity activity) {
        activity.startActivity(MainActivity.makeIntent(activity));
        activity.finish();
    }
}