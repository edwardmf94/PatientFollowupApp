package com.pe.patient.followup.view;

public interface LoginActivityView {
    void onLoginResult(Boolean success, String message);
    void onSetProgressBarVisibility(int visibility);
    void showError(String result);
}
