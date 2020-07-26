package com.pe.patient.followup.view;

import com.pe.patient.followup.model.PatientFile;

import java.util.ArrayList;

public interface PatientFileActivityView {
    void onSavePatientFileResult(Boolean success, String message);
    void onGetPatientFiles(Boolean success, ArrayList<PatientFile> patientFiles);
    void onDeletePatientFileResult(Boolean success, String message);
    void onSetProgressBarVisibility(int visibility);
}
