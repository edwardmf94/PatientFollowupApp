package com.pe.patient.followup.interactor;

import android.content.Context;

public interface LoginActivityInteractor {
    void doLogin(String username, String password, Boolean remember_session, Context context);
}
