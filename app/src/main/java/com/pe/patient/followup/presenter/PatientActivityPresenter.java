package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.model.Patient;

public interface PatientActivityPresenter {
    void updatePatient(Patient patient, Integer idPatient, Context context);
    void onUpdatePatientResult(Boolean success, String message);
    void getPatient(Integer idPatient, Context context);
    void onGetPatientResult(Boolean success, Patient patient);
    void setProgressBarVisiblity(int visiblity);
}
