package com.pe.patient.followup.presenter;

import android.content.Context;
import android.view.View;

import com.pe.patient.followup.interactor.PatientFileActivityInteractor;
import com.pe.patient.followup.interactor.PatientFileActivityInteractorImpl;
import com.pe.patient.followup.model.PatientFile;
import com.pe.patient.followup.view.PatientFileActivityView;

import java.util.ArrayList;

public class PatientFileActivityPresenterImpl implements PatientFileActivityPresenter {
    PatientFileActivityView activityView;
    PatientFileActivityInteractor interactor;

    public PatientFileActivityPresenterImpl(PatientFileActivityView activityView) {
        this.activityView = activityView;
        interactor = new PatientFileActivityInteractorImpl(this);
    }

    @Override
    public void savePatientFile(PatientFile patientFile, Integer idPatient, Context context) {
        this.setProgressBarVisiblity(View.VISIBLE);
        interactor.savePatientFile(patientFile, idPatient, context);
    }

    @Override
    public void onSavePatientFileResult(Boolean success, String message) {
        activityView.onSavePatientFileResult(success, message);
    }

    @Override
    public void getPatientFiles(Integer idPatient, Context context) {
        this.setProgressBarVisiblity(View.VISIBLE);
        interactor.getPatientFiles(idPatient, context);
    }

    @Override
    public void onGetPatientFiles(Boolean success, ArrayList<PatientFile> patientFiles) {
        activityView.onGetPatientFiles(success, patientFiles);
    }

    @Override
    public void deletePatientFile(Integer idPatientFile, Context context) {
        this.setProgressBarVisiblity(View.VISIBLE);
        interactor.deletePatientFile(idPatientFile, context);
    }

    @Override
    public void onDeletePatientFileResult(Boolean success, String message) {
        activityView.onDeletePatientFileResult(success,message);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        activityView.onSetProgressBarVisibility(visiblity);
    }
}
