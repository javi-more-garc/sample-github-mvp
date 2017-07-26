package com.jmgits.sample.github.mvp.user.presenter.view;

import com.jmgits.sample.github.mvp.user.model.view.UserDetails;

/**
 * Created by javi-more-garc on 26/07/17.
 */

public interface UserView {

    void userDetailsRetrieved(UserDetails userDetails);
    void userDetailsNotFound(String username);
    void userDetailsFailed(String username, Throwable throwable);
}
