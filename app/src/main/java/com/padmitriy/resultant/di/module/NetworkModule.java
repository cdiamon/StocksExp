package com.padmitriy.resultant.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.padmitriy.resultant.BuildConfig;
import com.padmitriy.resultant.network.ResultantApi;
import com.padmitriy.resultant.util.PreferencesManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String JSON_HEADER = "application/json";

    @Provides
    @Singleton
    public Gson provideApiGson() {
        return new GsonBuilder()
                .serializeNulls()
                .create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }

        return httpClient
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitApiRx(PreferencesManager preferenceManager, OkHttpClient httpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(preferenceManager.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    ResultantApi provideApi(Retrofit retrofit) {
        return retrofit.create(ResultantApi.class);
    }
}
