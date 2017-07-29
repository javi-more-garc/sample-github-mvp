package com.jmgits.sample.github.mvp.user.config;

import com.jmgits.sample.github.mvp.App;
import com.jmgits.sample.github.mvp.user.model.client.GitHubClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by javi-more-garc on 27/07/17.
 */
@Module
public class UserModule {

    private App app;

    public UserModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    GitHubClient gitHubClient(Retrofit retrofit) {
        return retrofit.create(GitHubClient.class);
    }
}
