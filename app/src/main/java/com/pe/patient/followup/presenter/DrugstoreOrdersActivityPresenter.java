package com.pe.patient.followup.presenter;

import android.content.Context;

import com.pe.patient.followup.model.DrugstoreOrder;

import java.util.ArrayList;

public interface DrugstoreOrdersActivityPresenter {
    void getDrugstoreOrders(Integer idPatient, Context context);
    void onGetDrugstoreOrdersResult(Boolean success, ArrayList<DrugstoreOrder> drugstoreOrder);
    void setProgressBarVisibility(int visibility);
}
