package com.pe.patient.followup.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.DrugstoreOrder;
import com.pe.patient.followup.presenter.DrugstoreOrdersActivityPresenter;
import com.pe.patient.followup.presenter.DrugstoreOrdersActivityPresenterImpl;

import java.util.ArrayList;

public class DrugstoreOrdersActivity extends AppCompatActivity implements DrugstoreOrdersActivityView {
    private ListView lvDrugstoreOrders;
    private DrugstoreOrdersAdapter drugstoreOrdersAdapter;
    private FrameLayout progressOverlay;
    private DrugstoreOrdersActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugstore_orders);

        progressOverlay = findViewById(R.id.progress_overlay);
        presenter = new DrugstoreOrdersActivityPresenterImpl(this);

        getSupportActionBar().setTitle(R.string.toolbar_drugstore_orders_activity);
        lvDrugstoreOrders = (ListView) findViewById(R.id.lvDrugstoreOrders);
        drugstoreOrdersAdapter = new DrugstoreOrdersAdapter(this, null);
        lvDrugstoreOrders.setAdapter(drugstoreOrdersAdapter);
        presenter.getDrugstoreOrders(1, this.getApplicationContext());
    }

    @Override
    public void onGetDrugstoreOrdersResult(Boolean success, ArrayList<DrugstoreOrder> drugstoreOrder) {
        presenter.setProgressBarVisibility(View.INVISIBLE);
        if(success){
            drugstoreOrdersAdapter.setListItems(drugstoreOrder);
            drugstoreOrdersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressOverlay.setVisibility(visibility);
    }
}
