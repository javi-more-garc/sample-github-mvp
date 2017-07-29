package com.jmgits.sample.github.mvp.base.config;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jmgits.sample.github.mvp.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by javi-more-garc on 27/07/17.
 */
@Module
public class BaseModule {

    private App app;

    public BaseModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Retrofit retrofit() {

        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
