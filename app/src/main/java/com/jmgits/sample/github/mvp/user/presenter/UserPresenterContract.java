package com.jmgits.sample.github.mvp.user.presenter;

import com.jmgits.sample.github.mvp.user.model.view.UserDetails;

/**
 * Created by javi-more-garc on 26/07/17.
 */

public interface UserPresenterContract {

    void setView(UserPresenterContract.View userView);

    void getDetailsByUsername(String username);

    //
    // model

    interface Model {

        void getDetailsByUsername(String username, UserPresenterContract.UserDetailsListener callback);
    }

    //
    // view

    interface View {

        void userDetailsRetrieved(UserDetails userDetails);
        void userDetailsNotFound(String username);
        void userDetailsFailed(String username, Throwable throwable);
    }

    //
    // listeners

    interface UserDetailsListener {

        void onSuccess(UserDetails userDetails);
        void onErrorNotFound(String username);
        void onError(String username, Throwable throwable);
    }
}
