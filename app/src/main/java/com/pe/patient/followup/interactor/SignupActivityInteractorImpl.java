package com.pe.patient.followup.interactor;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pe.patient.followup.model.User;
import com.pe.patient.followup.presenter.SignupActivityPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivityInteractorImpl implements SignupActivityInteractor {
    private SignupActivityPresenter presenter;

    public SignupActivityInteractorImpl(SignupActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveUser(User user, Context context) {
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://209.45.40.86:8080/followup/signup";
        final String username = user.getUsername();
        final String password = user.getPassword();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(!_response.has("error")){
                        User user2 = new User();
                        user2.setUsername(_response.getString("usuCip"));
                        user2.setToken(_response.getString("token"));
                        presenter.onSaveUserResult(true, "Usuario creado correctamente", user2);
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onSaveUserResult(false, _response.getString("message"), null);
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //presenter.onUpdatePatientResult(false, "Ha ocurrido un error de conexion al servido");
                try{
                    JSONObject _response = new JSONObject(new String(error.networkResponse.data));
                    //Log.i("erroraipp",error.networkResponse.data);
                    presenter.onSaveUserResult(false,_response.getString("message"), null);
                }catch(JSONException e){
                    presenter.onSaveUserResult(false,"El servicio no esta disponible en estos momentops", null);
                }
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username );
                params.put("password", password );
                return params;
            };
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(stringRequest);
    }
}
