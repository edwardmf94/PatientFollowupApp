package com.pe.patient.followup.presenter;

import android.content.Context;
import android.view.View;

import com.pe.patient.followup.interactor.DrugstoreOrdersActivityInteractor;
import com.pe.patient.followup.interactor.DrugstoreOrdersActivityInteractorImpl;
import com.pe.patient.followup.model.DrugstoreOrder;
import com.pe.patient.followup.view.DrugstoreOrdersActivityView;

import java.util.ArrayList;

public class DrugstoreOrdersActivityPresenterImpl implements DrugstoreOrdersActivityPresenter {
    private DrugstoreOrdersActivityView activityView;
    private DrugstoreOrdersActivityInteractor interactor;

    public DrugstoreOrdersActivityPresenterImpl(DrugstoreOrdersActivityView activityView) {
        this.activityView = activityView;
        interactor = new DrugstoreOrdersActivityInteractorImpl(this);
    }

    @Override
    public void getDrugstoreOrders(Integer idPatient, Context context) {
        this.setProgressBarVisibility(View.VISIBLE);
        interactor.getDrugstoreOrders(idPatient, context);
    }

    @Override
    public void onGetDrugstoreOrdersResult(Boolean success, ArrayList<DrugstoreOrder> drugstoreOrder) {
        activityView.onGetDrugstoreOrdersResult(success, drugstoreOrder);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        activityView.onSetProgressBarVisibility(visibility);
    }
}
