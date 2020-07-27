package com.pe.patient.followup.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.pe.patient.followup.interactor.SignupActivityInteractor;
import com.pe.patient.followup.interactor.SignupActivityInteractorImpl;
import com.pe.patient.followup.model.User;
import com.pe.patient.followup.view.SignupActivityView;

public class SignupActivityPresenterImpl implements SignupActivityPresenter {
    private SignupActivityView activityView;
    private SignupActivityInteractor interactor;
    private SharedPreferences settings;

    public SignupActivityPresenterImpl(SignupActivityView activityView, SharedPreferences settings) {
        this.activityView = activityView;
        this.settings = settings;
        interactor = new SignupActivityInteractorImpl(this);
    }

    @Override
    public void saveUser(User user, Context context) {
        interactor.saveUser(user, context);
    }

    @Override
    public void onSaveUserResult(Boolean success, String message, User user) {
        if(success){
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("login_username", user.getUsername());
            editor.putString("login_token", user.getToken());
            editor.commit();
        }
        activityView.onSaveUserResult(success,message);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        activityView.onSetProgressBarVisibility(visiblity);
    }
}
