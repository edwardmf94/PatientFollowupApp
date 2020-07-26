package com.pe.patient.followup.view;

import com.pe.patient.followup.model.PatientRecipient;

import java.util.ArrayList;

public interface RecipientsActivityView {
    void onGetPatientRecipientsResult(Boolean success, ArrayList<PatientRecipient> patientFiles);
    void onSetProgressBarVisibility(int visibility);
}
