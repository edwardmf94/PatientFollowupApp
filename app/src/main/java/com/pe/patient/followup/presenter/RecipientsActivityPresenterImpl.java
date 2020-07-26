package com.pe.patient.followup.presenter;

import android.content.Context;
import android.view.View;

import com.pe.patient.followup.interactor.RecipientsActivityInteractor;
import com.pe.patient.followup.interactor.RecipientsActivityInteractorImpl;
import com.pe.patient.followup.model.PatientRecipient;
import com.pe.patient.followup.view.RecipientsActivityView;

import java.util.ArrayList;

public class RecipientsActivityPresenterImpl implements RecipientsActivityPresenter {
    RecipientsActivityView activityView;
    RecipientsActivityInteractor interactor;

    public RecipientsActivityPresenterImpl(RecipientsActivityView activityView) {
        this.activityView = activityView;
        interactor = new RecipientsActivityInteractorImpl(this);
    }

    @Override
    public void getPatientRecipients(Integer idPatient, Context context) {
        this.setProgressBarVisiblity(View.VISIBLE);
        interactor.getPatientRecipients(idPatient,context);
    }

    @Override
    public void onGetPatientRecipientsResult(Boolean success, ArrayList<PatientRecipient> patientRecipient) {
        activityView.onGetPatientRecipientsResult(success, patientRecipient);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        activityView.onSetProgressBarVisibility(visiblity);
    }
}
