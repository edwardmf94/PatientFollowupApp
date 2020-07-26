package com.pe.patient.followup.presenter;

import android.content.Context;

public interface LoginActivityPresenter {
    void doLogin(String username, String password, Boolean remember_session, Context context);
    void setProgressBarVisiblity(int visiblity);
    void onLoginResult(Boolean success, String message);
    void showError(String result);
}
