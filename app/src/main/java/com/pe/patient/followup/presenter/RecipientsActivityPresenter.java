package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.model.PatientRecipient;

import java.util.ArrayList;

public interface RecipientsActivityPresenter {
    void getPatientRecipients(Integer idPatient, Context context);
    void onGetPatientRecipientsResult(Boolean success, ArrayList<PatientRecipient> patientRecipient);
    void setProgressBarVisiblity(int visiblity);
}
