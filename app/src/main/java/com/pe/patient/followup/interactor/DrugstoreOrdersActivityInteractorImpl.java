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
import com.pe.patient.followup.model.DrugstoreOrder;
import com.pe.patient.followup.model.PatientRecipient;
import com.pe.patient.followup.presenter.DrugstoreOrdersActivityPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrugstoreOrdersActivityInteractorImpl implements DrugstoreOrdersActivityInteractor {
    private DrugstoreOrdersActivityPresenter presenter;

    public DrugstoreOrdersActivityInteractorImpl(DrugstoreOrdersActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDrugstoreOrders(Integer idPatient, Context context) {
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2/api_rest/obtener_entrega_medicamentos.php";
        final Integer id_patient = idPatient;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(_response.getBoolean("success")){
                        ArrayList<DrugstoreOrder> drugstoreOrders= new ArrayList<>();
                        JSONArray items = _response.getJSONArray("items");
                        for (int i = 0; i < items.length(); i++) {
                            JSONObject item = items.getJSONObject(i);
                            drugstoreOrders.add(new DrugstoreOrder(item.getString("detalle"),item.getString("fecha_tramitado"), item.getString("fecha_pendiente"), item.getString("fecha_preparacion")));
                        }
                        presenter.onGetDrugstoreOrdersResult(true,drugstoreOrders);
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onGetDrugstoreOrdersResult(false, null);
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.onGetDrugstoreOrdersResult(false, null);
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
