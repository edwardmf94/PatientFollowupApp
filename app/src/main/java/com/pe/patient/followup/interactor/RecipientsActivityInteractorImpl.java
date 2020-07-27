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
import com.pe.patient.followup.model.PatientFile;
import com.pe.patient.followup.model.PatientRecipient;
import com.pe.patient.followup.presenter.RecipientsActivityPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipientsActivityInteractorImpl implements RecipientsActivityInteractor {
    private RecipientsActivityPresenter presenter;

    public RecipientsActivityInteractorImpl(RecipientsActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPatientRecipients(Integer idPatient, Context context) {
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2/api_rest/obtener_paciente_beneficiarios.php";
        final Integer id_patient = idPatient;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(_response.getBoolean("success")){
                        ArrayList<PatientRecipient> patientRecipients = new ArrayList<>();
                        JSONArray items = _response.getJSONArray("items");
                        for (int i = 0; i < items.length(); i++) {
                            JSONObject item = items.getJSONObject(i);
                            patientRecipients.add(new PatientRecipient(item.getString("cif"),item.getString("nombre"), item.getString("tarifa"), item.getString("iafas")));
                        }
                        presenter.onGetPatientRecipientsResult(true,patientRecipients);
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onGetPatientRecipientsResult(false, null);
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.onGetPatientRecipientsResult(false, null);
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_paciente", id_patient.toString() );
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
