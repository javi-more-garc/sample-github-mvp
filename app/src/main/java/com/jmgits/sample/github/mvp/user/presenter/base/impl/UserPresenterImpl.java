package com.jmgits.sample.github.mvp.user.presenter.base.impl;

import com.jmgits.sample.github.mvp.user.model.view.UserDetails;
import com.jmgits.sample.github.mvp.user.model.service.UserService;
import com.jmgits.sample.github.mvp.user.presenter.base.UserPresenter;
import com.jmgits.sample.github.mvp.user.presenter.model.UserModel;
import com.jmgits.sample.github.mvp.user.presenter.view.UserView;

/**
 * Created by javi-more-garc on 26/07/17.
 */

public class UserPresenterImpl implements UserPresenter, UserPresenter.UserDetailsListener {

    private final UserView mUserView;
    private final UserModel mUserModel;

    public UserPresenterImpl(UserView userView) {
        this.mUserView = userView;
        this.mUserModel = new UserService();
    }

    @Override
    public void getDetailsByUsername(String username) {
        mUserModel.getDetailsByUsername(username, this);
    }

    @Override
    public void onSuccess(UserDetails userDetails) {
        mUserView.userDetailsRetrieved(userDetails);
    }

    @Override
    public void onErrorNotFound(String username) {
        mUserView.userDetailsNotFound(username);
    }

    @Override
    public void onError(String username, Throwable throwable) {
        mUserView.userDetailsFailed(username, throwable);
    }
}
