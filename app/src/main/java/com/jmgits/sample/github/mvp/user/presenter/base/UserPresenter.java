package com.jmgits.sample.github.mvp.user.presenter.base;

import com.jmgits.sample.github.mvp.user.model.view.UserDetails;

/**
 * Created by javi-more-garc on 26/07/17.
 */

public interface UserPresenter {

    void getDetailsByUsername(String username);

    interface UserDetailsListener {

        void onSuccess(UserDetails userDetails);
        void onErrorNotFound(String username);
        void onError(String username, Throwable throwable);
    }
}
