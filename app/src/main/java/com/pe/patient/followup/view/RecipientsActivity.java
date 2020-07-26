package com.pe.patient.followup.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.PatientRecipient;
import com.pe.patient.followup.presenter.RecipientsActivityPresenter;
import com.pe.patient.followup.presenter.RecipientsActivityPresenterImpl;

import java.util.ArrayList;

public class RecipientsActivity extends AppCompatActivity implements RecipientsActivityView {
    private ListView lvPatientRecipients;
    private RecipientsAdapter recipientsAdapter;
    private FrameLayout progressOverlay;
    private RecipientsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipients);

        progressOverlay = findViewById(R.id.progress_overlay);
        presenter = new RecipientsActivityPresenterImpl(this);

        getSupportActionBar().setTitle(R.string.toolbar_patient_recipients_activity);
        lvPatientRecipients = (ListView) findViewById(R.id.lvPatientRecipients);
        recipientsAdapter = new RecipientsAdapter(this, null);
        lvPatientRecipients.setAdapter(recipientsAdapter);
        presenter.getPatientRecipients(1, this.getApplicationContext());
    }

    @Override
    public void onGetPatientRecipientsResult(Boolean success, ArrayList<PatientRecipient> patientFiles) {
        presenter.setProgressBarVisiblity(View.INVISIBLE);
        if(success){
            recipientsAdapter.setListItems(patientFiles);
            recipientsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressOverlay.setVisibility(visibility);
    }
}
