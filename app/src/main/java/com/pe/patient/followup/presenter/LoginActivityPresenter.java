package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.model.User;

public interface LoginActivityPresenter {
    void doLogin(String username, String password, Boolean remember_session, Context context);
    void setProgressBarVisiblity(int visiblity);
    void onLoginResult(Boolean success, String message, User user);
    void showError(String result);
}
