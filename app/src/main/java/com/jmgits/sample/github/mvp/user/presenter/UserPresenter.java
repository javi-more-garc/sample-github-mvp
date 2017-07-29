package com.jmgits.sample.github.mvp.user.presenter;

import com.jmgits.sample.github.mvp.user.model.service.UserService;
import com.jmgits.sample.github.mvp.user.model.view.UserDetails;
import com.jmgits.sample.github.mvp.user.presenter.UserPresenterContract;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by javi-more-garc on 26/07/17.
 */
@Singleton
public class UserPresenter implements UserPresenterContract, UserPresenterContract.UserDetailsListener {

    private final UserPresenterContract.Model mUserModel;
    private UserPresenterContract.View mUserView;

    @Inject
    public UserPresenter(UserService userService) {
        this.mUserModel = userService;
    }

    @Override
    public void setView(UserPresenterContract.View userView) {
        this.mUserView = userView;
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
