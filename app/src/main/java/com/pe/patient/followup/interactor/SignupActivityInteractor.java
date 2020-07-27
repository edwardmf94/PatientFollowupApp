package com.pe.patient.followup.interactor;

import android.content.Context;

import com.pe.patient.followup.model.User;

public interface SignupActivityInteractor {
    void saveUser(User user, Context context);
}
