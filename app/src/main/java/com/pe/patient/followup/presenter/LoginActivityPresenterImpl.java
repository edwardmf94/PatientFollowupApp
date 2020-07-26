package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.interactor.LoginActivityInteractor;
import com.pe.patient.followup.interactor.LoginActivityInteractorImpl;
import com.pe.patient.followup.view.LoginActivityView;

public class LoginActivityPresenterImpl implements LoginActivityPresenter {

    private LoginActivityView activityView;
    private LoginActivityInteractor interactor;

    public LoginActivityPresenterImpl(LoginActivityView activityView) {
        this.activityView = activityView;
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
    public void onLoginResult(Boolean success, String message) {
        activityView.onLoginResult(success,message);
    }

    @Override
    public void showError(String result) {

    }
}
