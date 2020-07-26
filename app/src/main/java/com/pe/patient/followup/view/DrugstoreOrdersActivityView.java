package com.pe.patient.followup.view;

import com.pe.patient.followup.model.DrugstoreOrder;

import java.util.ArrayList;

public interface DrugstoreOrdersActivityView {
    void onGetDrugstoreOrdersResult(Boolean success, ArrayList<DrugstoreOrder> drugstoreOrder);
    void onSetProgressBarVisibility(int visibility);
}
