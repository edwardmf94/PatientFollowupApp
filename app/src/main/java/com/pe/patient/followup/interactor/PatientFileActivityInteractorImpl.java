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
import com.pe.patient.followup.presenter.PatientFileActivityPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PatientFileActivityInteractorImpl implements PatientFileActivityInteractor {
    PatientFileActivityPresenter presenter;

    public PatientFileActivityInteractorImpl(PatientFileActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void savePatientFile(PatientFile patientFile, Integer idPatient, Context context) {
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2/api_rest/renovar_ficha_paciente.php";
        final Integer id_patient = idPatient;
        final String especialidad = patientFile.getDepartment();
        final String farmacia = patientFile.getDrugstore();
        final String diagnostico = patientFile.getPrimaryDiagnosis();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(_response.getBoolean("success")){
                        presenter.onSavePatientFileResult(true, "Datos actualizados correctamente");
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onSavePatientFileResult(false, "Ha ocurrido un error al actualizar datos");
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                    presenter.onSavePatientFileResult(false, "Ha ocurrido un error en leer la respuesta del servidor");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.onSavePatientFileResult(false, "Ha ocurrido un error de conexion al servido");
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_paciente", id_patient.toString() );
                params.put("especialidad", especialidad );
                params.put("farmacia", farmacia);
                params.put("diagnostico", diagnostico);
                return params;
            };
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(stringRequest);
    }

    @Override
    public void getPatientFiles(Integer idPatient, Context context) {
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2/api_rest/obtener_ficha_paciente.php";
        final Integer id_patient = idPatient;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(_response.getBoolean("success")){
                        ArrayList<PatientFile> patientFiles = new ArrayList<>();
                        JSONArray items = _response.getJSONArray("items");
                        for (int i = 0; i < items.length(); i++) {
                            JSONObject item = items.getJSONObject(i);
                            patientFiles.add(new PatientFile(item.getInt("id"),item.getString("especialidad"), item.getString("farmacia"), item.getString("especialidad")));
                        }
                        presenter.onGetPatientFiles(true, patientFiles);
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onGetPatientFiles(false, null);
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.onGetPatientFiles(false, null);
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

    @Override
    public void deletePatientFile(Integer idPatientFile, Context context) {
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2/api_rest/borrar_ficha_paciente.php";
        final Integer id_ficha = idPatientFile;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(_response.getBoolean("success")){
                        presenter.onDeletePatientFileResult(true, "Ficha borrada correctamente");
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onDeletePatientFileResult(false, "Ha ocurrido un error al actualizar datos");
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                    presenter.onDeletePatientFileResult(false, "Ha ocurrido un error en leer la respuesta del servidor");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.onDeletePatientFileResult(false, "Ha ocurrido un error de conexion al servido");
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_ficha", id_ficha.toString() );
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
