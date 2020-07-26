package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.model.PatientFile;

import java.util.ArrayList;

public interface PatientFileActivityPresenter {
    void savePatientFile(PatientFile patientFile, Integer idPatient, Context context);
    void onSavePatientFileResult(Boolean success, String message);
    void getPatientFiles(Integer idPatient, Context context);
    void onGetPatientFiles(Boolean success, ArrayList<PatientFile> patientFiles);
    void deletePatientFile(Integer idPatientFile, Context context);
    void onDeletePatientFileResult(Boolean success, String message);
    void setProgressBarVisiblity(int visiblity);
}
