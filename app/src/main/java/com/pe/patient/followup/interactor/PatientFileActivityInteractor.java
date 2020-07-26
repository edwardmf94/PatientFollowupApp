package com.pe.patient.followup.interactor;

import android.content.Context;

import com.pe.patient.followup.model.PatientFile;

public interface PatientFileActivityInteractor {
    void savePatientFile(PatientFile patientFile, Integer idPatient, Context context);
    void getPatientFiles(Integer idPatient, Context context);
    void deletePatientFile(Integer idPatientFile, Context context);
}
