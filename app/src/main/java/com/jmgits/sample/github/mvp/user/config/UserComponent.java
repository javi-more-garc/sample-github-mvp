package com.jmgits.sample.github.mvp.user.config;

import com.jmgits.sample.github.mvp.base.config.BaseModule;
import com.jmgits.sample.github.mvp.user.ui.activity.UserActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javi-more-garc on 27/07/17.
 */
@Singleton
@Component(modules = {BaseModule.class, UserModule.class})
public interface UserComponent {

    UserActivity inject(UserActivity userActivity);
}
