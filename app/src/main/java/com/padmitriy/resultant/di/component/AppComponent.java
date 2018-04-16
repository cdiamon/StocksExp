package com.padmitriy.resultant.di.component;

import com.padmitriy.resultant.ResultantApp;
import com.padmitriy.resultant.di.module.AppModule;
import com.padmitriy.resultant.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(ResultantApp resultantApp);

    void inject(MainActivity mainActivity);


    final class Initializer {
        public static AppComponent init(ResultantApp app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }

}
