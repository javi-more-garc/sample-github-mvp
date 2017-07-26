package com.jmgits.sample.github.mvp.user.model.client;

import com.jmgits.sample.github.mvp.user.model.view.UserDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by javi-more-garc on 27/07/17.
 */

public interface GitHubClient {

    @GET("/users/{username}")
    Observable<UserDetails> getDetailsByUsername(@Path("username") String username);
}
