package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.model.User;

public interface SignupActivityPresenter {
    void saveUser(User user, Context context);
    void onSaveUserResult(Boolean success, String message, User user);
    void setProgressBarVisiblity(int visiblity);
}
