package com.pe.patient.followup.view;

import com.pe.patient.followup.model.Patient;

public interface SignupActivityView {
    void onSaveUserResult(Boolean success, String message);
    void onSetProgressBarVisibility(int visibility);
}
