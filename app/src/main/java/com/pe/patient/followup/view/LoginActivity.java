package com.pe.patient.followup.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pe.patient.followup.R;
import com.pe.patient.followup.presenter.LoginActivityPresenter;
import com.pe.patient.followup.presenter.LoginActivityPresenterImpl;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {
    private EditText edtUsername;
    private EditText edtPassword;
    private CheckBox chkStayLogged;
    private FrameLayout progressOverlay;
    private Context mContext;
    private LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        //chkStayLogged = (CheckBox) findViewById(R.id.chkStayLogged);

        SharedPreferences settings = getSharedPreferences("iafas_pref", Context.MODE_PRIVATE);


        progressOverlay = findViewById(R.id.progress_overlay);
        presenter = new LoginActivityPresenterImpl(this, settings);
        presenter.setProgressBarVisiblity(View.INVISIBLE);
    }

    public void btnLogin(View view){
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        //Boolean stay_logged = chkStayLogged.isChecked();
        presenter.setProgressBarVisiblity(View.VISIBLE);
        presenter.doLogin(username,password,true, this.getApplicationContext());
    }

    public void btnSignup(View view){
        Intent i = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(i);
    }

    @Override
    public void onLoginResult(Boolean success, String message) {
        presenter.setProgressBarVisiblity(View.INVISIBLE);
        if(success){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressOverlay.setVisibility(visibility);
    }

    @Override
    public void showError(String result) {

    }
}
