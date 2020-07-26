package com.pe.patient.followup.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.Patient;
import com.pe.patient.followup.presenter.PatientActivityPresenter;
import com.pe.patient.followup.presenter.PatientActivityPresenterImpl;

public class PatientActivity extends AppCompatActivity implements PatientActivityView {
    private EditText edtDni;
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtBrithDate;
    private Spinner spnGender;
    private Spinner spnDepartment;
    private Spinner spnProvince;
    private EditText edtDistrict;
    private EditText edtAddress;
    private EditText edtComment;
    private FrameLayout progressOverlay;

    PatientActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        edtDni = (EditText) findViewById(R.id.edtDni);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtBrithDate = (EditText) findViewById(R.id.edtBirthDate);
        spnGender = (Spinner) findViewById(R.id.spnGender);
        spnDepartment = (Spinner) findViewById(R.id.spnDepartment);
        spnProvince = (Spinner) findViewById(R.id.spnProvince);
        edtDistrict = (EditText) findViewById(R.id.edtDistrict);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtComment = (EditText) findViewById(R.id.edtComment);

        progressOverlay = findViewById(R.id.progress_overlay);
        presenter = new PatientActivityPresenterImpl(this);
        presenter.setProgressBarVisiblity(View.VISIBLE);

        getSupportActionBar().setTitle(R.string.toolbar_patient_activity);

        presenter.getPatient(1, this.getApplicationContext());
    }

    public void btnSavePatient(View view){
        Patient patient = new Patient();
        patient.setDni(edtDni.getText().toString());
        patient.setNamem(edtName.getText().toString());
        patient.setPhone(edtPhone.getText().toString());
        patient.setBirthDate(edtBrithDate.getText().toString());
        patient.setGender(spnGender.getSelectedItem().toString());
        patient.setDepartment(spnDepartment.getSelectedItem().toString());
        patient.setProvince(spnProvince.getSelectedItem().toString());
        patient.setDistrict(edtDistrict.getText().toString());
        patient.setAddress(edtAddress.getText().toString());
        patient.setComment(edtComment.getText().toString());
        presenter.setProgressBarVisiblity(View.VISIBLE);
        presenter.updatePatient(patient, 1, this.getApplicationContext());
    }

    @Override
    public void onUpdatePatientResult(Boolean success, String message) {
        presenter.setProgressBarVisiblity(View.INVISIBLE);
        if(success){
            //acciones cuando es correcto
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetPatientResult(Boolean success, Patient patient) {
        presenter.setProgressBarVisiblity(View.INVISIBLE);
        if(success){
            edtDni.setText(patient.getDni());
            edtName.setText(patient.getNamem());
            edtPhone.setText(patient.getPhone());
            edtBrithDate.setText(patient.getBirthDate());
            //gender
            //department
            //province
            edtDistrict.setText(patient.getDistrict());
            edtAddress.setText(patient.getAddress());
            edtComment.setText(patient.getComment());
        }else{
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error al obtener la informacion del paciente", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressOverlay.setVisibility(visibility);
    }
}
