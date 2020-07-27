package com.pe.patient.followup.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.User;
import com.pe.patient.followup.presenter.SignupActivityPresenter;
import com.pe.patient.followup.presenter.SignupActivityPresenterImpl;

public class SignupActivity extends AppCompatActivity implements SignupActivityView {
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtPassword2;
    private FrameLayout progressOverlay;
    private SignupActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword2 = (EditText) findViewById(R.id.edtPassword2);
        //chkStayLogged = (CheckBox) findViewById(R.id.chkStayLogged);

        SharedPreferences settings = getSharedPreferences("iafas_pref", Context.MODE_PRIVATE);


        progressOverlay = findViewById(R.id.progress_overlay);
        presenter = new SignupActivityPresenterImpl(this, settings);
        presenter.setProgressBarVisiblity(View.INVISIBLE);
    }

    public void btnSave(View view){
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String password2 = edtPassword2.getText().toString();
        if(password.equals(password2)){
            //Boolean stay_logged = chkStayLogged.isChecked();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            presenter.setProgressBarVisiblity(View.VISIBLE);
            presenter.saveUser(user, this.getApplicationContext());
        }else{
            Toast.makeText(getApplicationContext(),"Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveUserResult(Boolean success, String message) {
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
}
