package com.jmgits.sample.github.mvp.user.model.service;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jmgits.sample.github.mvp.user.model.client.GitHubClient;
import com.jmgits.sample.github.mvp.user.model.view.UserDetails;
import com.jmgits.sample.github.mvp.user.presenter.base.UserPresenter;
import com.jmgits.sample.github.mvp.user.presenter.model.UserModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by javi-more-garc on 26/07/17.
 */

public class UserService implements UserModel {

    private final GitHubClient mGitHubClient;

    public UserService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mGitHubClient = retrofit.create(GitHubClient.class);
    }

    @Override
    public void getDetailsByUsername(final String username, final UserPresenter.UserDetailsListener callback) {

        mGitHubClient.getDetailsByUsername(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDetails>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserDetails userDetails) {
                        callback.onSuccess(userDetails);
                    }

                    @Override
                    public void onError(Throwable t) {

                        if (t instanceof HttpException && ((HttpException) t).code() == 404){
                            callback.onErrorNotFound(username);
                            return;
                        }

                        callback.onError(username, t);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
