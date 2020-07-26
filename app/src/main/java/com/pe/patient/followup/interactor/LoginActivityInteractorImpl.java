package com.pe.patient.followup.interactor;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.DiskBasedCache;
import com.pe.patient.followup.model.User;
import com.pe.patient.followup.presenter.LoginActivityPresenter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivityInteractorImpl implements LoginActivityInteractor {
    public static final String PREF_NOMBRE = "SecurityPreferences";
    private LoginActivityPresenter presenter;
    RequestQueue requestQueue;


    public LoginActivityInteractorImpl(LoginActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void doLogin(String username, String password, Boolean remember_session, Context context) {
        //TODO CONNECTION API REST
        Log.i("doLogin","inicio");

        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:8080/login";
        final String usuario = username;
        final String contrasena = password;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(!_response.has("error")){
                        Log.i("SUCCESS","ACCESO CORRECTO");
                        User user = new User();
                        user.setUsername(_response.getString("usuCip"));
                        user.setToken(_response.getString("token"));
                        presenter.onLoginResult(true,"Acceso correcto", user);
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onLoginResult(false,_response.getString("message"), null);
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    JSONObject _response = new JSONObject(new String(error.networkResponse.data));
                    //Log.i("erroraipp",error.networkResponse.data);
                    presenter.onLoginResult(false,_response.getString("message"), null);
                }catch(JSONException e){
                    presenter.onLoginResult(false,"El servicio no esta disponible en estos momentops", null);
                }
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", usuario);
                params.put("password", contrasena);
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
