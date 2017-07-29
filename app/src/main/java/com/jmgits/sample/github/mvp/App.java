package com.jmgits.sample.github.mvp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.jmgits.sample.github.mvp.base.config.BaseModule;
import com.jmgits.sample.github.mvp.user.config.DaggerUserComponent;
import com.jmgits.sample.github.mvp.user.config.UserComponent;
import com.jmgits.sample.github.mvp.user.config.UserModule;

/**
 * Created by javi-more-garc on 27/07/17.
 */
public class App extends Application {

    private BaseModule baseModule;
    private UserModule userModule;

    private UserComponent mUserComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.baseModule = new BaseModule(this);
        this.userModule = new UserModule(this);
    }

    @VisibleForTesting
    protected UserComponent createUserComponent() {

        return DaggerUserComponent.builder()
                .baseModule(baseModule)
                .userModule(userModule)
                .build();
    }

    public static UserComponent getUserComponent(Context context) {
        App app = (App) context.getApplicationContext();

        if (app.mUserComponent == null) {
            app.mUserComponent = app.createUserComponent();
        }

        return app.mUserComponent;
    }
}