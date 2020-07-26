package com.pe.patient.followup.interactor;

import android.content.Context;

import com.pe.patient.followup.model.Patient;

public interface PatientActivityInteractor {
    void updatePatient(Patient patient, Integer idPatient, Context context);
    void getPatient(Integer idPatient, Context context);
}
