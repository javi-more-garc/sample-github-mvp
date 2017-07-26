package com.jmgits.sample.github.mvp.user.presenter.model;

import com.jmgits.sample.github.mvp.user.presenter.base.UserPresenter;

/**
 * Created by javi-more-garc on 26/07/17.
 */

public interface UserModel {

    void getDetailsByUsername(String username, UserPresenter.UserDetailsListener callback);
}
