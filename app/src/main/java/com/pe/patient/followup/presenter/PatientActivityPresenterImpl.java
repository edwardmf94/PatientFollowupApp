package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.interactor.PatientActivityInteractor;
import com.pe.patient.followup.interactor.PatientActivityInteractorImpl;
import com.pe.patient.followup.model.Patient;
import com.pe.patient.followup.view.PatientActivityView;

public class PatientActivityPresenterImpl implements PatientActivityPresenter {
    private PatientActivityView activityView;
    private PatientActivityInteractor interactor;

    public PatientActivityPresenterImpl(PatientActivityView activityView) {
        this.activityView = activityView;
        interactor = new PatientActivityInteractorImpl(this);
    }

    @Override
    public void updatePatient(Patient patient, Integer idPatient, Context context) {
        interactor.updatePatient(patient,idPatient,context);
    }

    @Override
    public void onUpdatePatientResult(Boolean success, String message) {
        activityView.onUpdatePatientResult(success, message);
    }

    @Override
    public void getPatient(Integer idPatient, Context context) {
        interactor.getPatient(idPatient, context);
    }

    @Override
    public void onGetPatientResult(Boolean success, Patient patient) {
        activityView.onGetPatientResult(success,patient);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        activityView.onSetProgressBarVisibility(visiblity);
    }
}
