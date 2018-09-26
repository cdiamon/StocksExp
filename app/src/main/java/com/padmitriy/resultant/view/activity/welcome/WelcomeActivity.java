package com.padmitriy.resultant.view.activity.welcome;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.padmitriy.resultant.R;
import com.padmitriy.resultant.mvvm.BaseViewModelActivity;
import com.padmitriy.resultant.mvvm.ViewModel;

public class WelcomeActivity extends BaseViewModelActivity {

    private Handler handler;
    private WelcomeViewModel welcomeViewModel;


    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(splashRunnable, 1500);
    }

    private Runnable splashRunnable = () -> {
        welcomeViewModel.onLoadingTimerFinished(this);
    };

    @Nullable
    @Override
    protected ViewModel createViewModel(@Nullable ViewModel.State savedViewModelState) {
        welcomeViewModel = new WelcomeViewModel(savedViewModelState);
        return welcomeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        handler = new Handler();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(splashRunnable);
        super.onPause();
    }
}
