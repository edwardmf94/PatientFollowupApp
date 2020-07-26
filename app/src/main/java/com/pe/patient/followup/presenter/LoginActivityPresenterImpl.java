package com.pe.patient.followup.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.pe.patient.followup.interactor.LoginActivityInteractor;
import com.pe.patient.followup.interactor.LoginActivityInteractorImpl;
import com.pe.patient.followup.model.User;
import com.pe.patient.followup.view.LoginActivityView;

public class LoginActivityPresenterImpl implements LoginActivityPresenter {

    private LoginActivityView activityView;
    private LoginActivityInteractor interactor;
    private SharedPreferences settings;

    public LoginActivityPresenterImpl(LoginActivityView activityView, SharedPreferences settings) {
        this.activityView = activityView;
        this.settings = settings;
        interactor = new LoginActivityInteractorImpl(this);
    }

    @Override
    public void doLogin(String username, String password, Boolean remember_session, Context context) {
        interactor.doLogin(username, password, remember_session, context);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        activityView.onSetProgressBarVisibility(visiblity);
    }

    @Override
    public void onLoginResult(Boolean success, String message, User user) {
        if(success){
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("login_username", user.getUsername());
            editor.putString("login_token", user.getToken());
            editor.commit();
        }
        activityView.onLoginResult(success,message);
    }

    @Override
    public void showError(String result) {

    }
}
