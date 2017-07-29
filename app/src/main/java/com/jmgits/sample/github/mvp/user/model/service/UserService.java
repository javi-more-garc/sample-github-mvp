package com.jmgits.sample.github.mvp.user.model.service;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jmgits.sample.github.mvp.user.model.client.GitHubClient;
import com.jmgits.sample.github.mvp.user.model.view.UserDetails;
import com.jmgits.sample.github.mvp.user.presenter.UserPresenterContract;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by javi-more-garc on 26/07/17.
 */
@Singleton
public class UserService implements UserPresenterContract.Model {

    private final GitHubClient mGitHubClient;

    @Inject
    public UserService(GitHubClient mGitHubClient) {
        this.mGitHubClient = mGitHubClient;
    }

    @Override
    public void getDetailsByUsername(final String username, final UserPresenterContract.UserDetailsListener callback) {

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
