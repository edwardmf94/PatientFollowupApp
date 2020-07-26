package com.pe.patient.followup.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.PatientFile;
import com.pe.patient.followup.presenter.PatientFileActivityPresenter;
import com.pe.patient.followup.presenter.PatientFileActivityPresenterImpl;

import java.util.ArrayList;

public class PatientFileActivity extends AppCompatActivity implements PatientFileActivityView {
    private ListView lvPatientFileList;
    private PatientFileAdapter fileAdapter;
    private FrameLayout progressOverlay;
    private Spinner spnDepartment;
    private Spinner spnDrugstore;
    private Spinner spnPrimaryDiagnosis;
    private PatientFileActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_file);

        spnDepartment = (Spinner) findViewById(R.id.spnDepartment);
        spnDrugstore = (Spinner) findViewById(R.id.spnDrugstore);
        spnPrimaryDiagnosis = (Spinner) findViewById(R.id.spnPrimaryDiagnosis);

        progressOverlay = findViewById(R.id.progress_overlay);
        presenter = new PatientFileActivityPresenterImpl(this);

        //presenter.setProgressBarVisiblity(View.VISIBLE);

        getSupportActionBar().setTitle(R.string.toolbar_patient_file_activity);
        lvPatientFileList = (ListView) findViewById(R.id.lvPatientFileList);
        fileAdapter = new PatientFileAdapter(this, null, presenter);
        lvPatientFileList.setAdapter(fileAdapter);
        presenter.getPatientFiles(1, this.getApplicationContext());
    }

    public void btnSavePatientFile(View view){
        PatientFile patientFile = new PatientFile(0,spnDepartment.getSelectedItem().toString(),spnDrugstore.getSelectedItem().toString(),spnPrimaryDiagnosis.getSelectedItem().toString());
        presenter.setProgressBarVisiblity(View.VISIBLE);
        presenter.savePatientFile(patientFile,1,this.getApplicationContext());
    }

    @Override
    public void onSavePatientFileResult(Boolean success, String message) {
        //presenter.setProgressBarVisiblity(View.INVISIBLE);
        if(success){
            //spnDepartment.refre
            presenter.getPatientFiles(1, this.getApplicationContext());
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetPatientFiles(Boolean success, ArrayList<PatientFile> patientFiles) {
        presenter.setProgressBarVisiblity(View.INVISIBLE);
        if(success){
            fileAdapter.setListItems(patientFiles);
            fileAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDeletePatientFileResult(Boolean success, String message) {
        presenter.setProgressBarVisiblity(View.INVISIBLE);
        if(success){
            presenter.getPatientFiles(1, this.getApplicationContext());
            //presenter.deletePatientFile(1, this.getApplicationContext());
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressOverlay.setVisibility(visibility);
    }
}
