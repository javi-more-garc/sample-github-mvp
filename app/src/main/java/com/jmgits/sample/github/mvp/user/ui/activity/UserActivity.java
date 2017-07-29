package com.jmgits.sample.github.mvp.user.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jmgits.sample.github.mvp.App;
import com.jmgits.sample.github.mvp.R;
import com.jmgits.sample.github.mvp.user.model.view.UserDetails;
import com.jmgits.sample.github.mvp.user.presenter.UserPresenterContract;
import com.jmgits.sample.github.mvp.user.presenter.UserPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by javi-more-garc on 26/07/17.
 */
public class UserActivity extends AppCompatActivity implements UserPresenterContract.View {

    private static final String TAG = UserActivity.class.getSimpleName();

    @Bind(R.id.edit_text_username)
    EditText mUsernameEditText;

    @Bind(R.id.button_get_details)
    Button mGetDetailsButton;

    @Inject
    UserPresenter mUserPresenter;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ButterKnife.bind(this);

        mGetDetailsButton.setEnabled(false);
        mUsernameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // button enabled only when there's some username to search for
                mGetDetailsButton.setEnabled(!TextUtils.isEmpty(s.toString().trim()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mUsernameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // if action is done
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    // perform get details
                    mGetDetailsButton.callOnClick();
                    return true;
                }
                return false;
            }
        });

        App.getUserComponent(this).inject(this);
        mUserPresenter.setView(this);
    }

    @OnClick(R.id.button_get_details)
    public void doGetDetails(View view) {

        mProgressDialog = ProgressDialog.show(this, getString(R.string.get_details_in_progress), null);
        mProgressDialog.show();

        String username = mUsernameEditText.getText().toString();

        mUserPresenter.getDetailsByUsername(username);
    }

    @Override
    public void userDetailsRetrieved(UserDetails userDetails) {
        showFeedback(String.format(getString(R.string.get_details_result_ok), userDetails.getUsername(), userDetails.getName()));
    }

    @Override
    public void userDetailsNotFound(String username) {
        showFeedback(String.format(getString(R.string.get_details_result_not_found), username));
    }

    @Override
    public void userDetailsFailed(String username, Throwable throwable) {

        Log.e(TAG, throwable.getMessage());

        showFeedback(String.format(getString(R.string.get_details_result_error), username));
    }

    //
    // private methods

    private void showFeedback(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}





