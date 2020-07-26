package com.pe.patient.followup.view;

import com.pe.patient.followup.model.Patient;

public interface PatientActivityView {
    void onUpdatePatientResult(Boolean success, String message);
    void onGetPatientResult(Boolean success, Patient patient);
    void onSetProgressBarVisibility(int visibility);
}
