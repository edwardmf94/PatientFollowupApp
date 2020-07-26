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
import com.pe.patient.followup.model.Patient;
import com.pe.patient.followup.presenter.PatientActivityPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PatientActivityInteractorImpl implements PatientActivityInteractor {
    private PatientActivityPresenter presenter;

    public PatientActivityInteractorImpl(PatientActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updatePatient(Patient patient, Integer idPatient, Context context) {
        //CONSULTAR SERVICIOS WEB PARA MODIFICAR DATOS DE PACIENTE
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2/api_rest/modificar_paciente.php";
        final Integer id_patient = idPatient;
        final String dni = patient.getDni();
        final String nombre = patient.getNamem();
        final String telefono = patient.getPhone();
        final String fechaNacimiento = patient.getBirthDate();
        final String departamento = patient.getDepartment();
        final String provincia = patient.getProvince();
        final String distrito = patient.getDistrict();
        final String direccion = patient.getAddress();
        final String referencia = patient.getComment();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(_response.getBoolean("success")){
                        presenter.onUpdatePatientResult(true, "Datos actualizados correctamente");
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onUpdatePatientResult(false, "Ha ocurrido un error al actualizar datos");
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.onUpdatePatientResult(false, "Ha ocurrido un error de conexion al servido");
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_paciente", id_patient.toString() );
                params.put("dni", dni );
                params.put("nombre", nombre );
                params.put("telefono", telefono);
                params.put("fecha_nacimiento", fechaNacimiento);
                params.put("departamento", departamento);
                params.put("provincia", provincia);
                params.put("distrito", distrito);
                params.put("direccion", direccion);
                params.put("referencia", referencia);
                return params;
            };
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(stringRequest);
        presenter.onUpdatePatientResult(true, "Paciente modificado correctamente");
    }

    @Override
    public void getPatient(Integer idPatient, Context context) {
        //OBTENER INFRMACION DE DATOS DE PACIENTE
        RequestQueue request = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2/api_rest/obtener_paciente.php";
        final Integer id_patient = idPatient;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPUESTA", response);
                JSONObject _response = null;
                try{
                    _response = new JSONObject(response);
                    if(_response.getBoolean("success")){
                        Patient patient = new Patient();
                        patient.setDni(_response.getString("dni"));
                        patient.setNamem(_response.getString("nombre"));
                        patient.setPhone(_response.getString("telefono"));
                        patient.setBirthDate(_response.getString("fecha_nacimiento"));
                        patient.setDepartment(_response.getString("departamento"));
                        patient.setProvince(_response.getString("provincia"));
                        patient.setDistrict(_response.getString("distrito"));
                        patient.setAddress(_response.getString("direccion"));
                        patient.setComment(_response.getString("referencia"));
                        presenter.onGetPatientResult(true, patient);
                    }else{
                        Log.i("SUCCESS","CREDENCIALES INCORRECTAS");
                        presenter.onGetPatientResult(false, null);
                    }
                }catch(JSONException e){
                    //Toast.makeText(mContext,"HA OCURRIDO UN ERRROR AL PARSEAR LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.i("SUCCESS","ERROR HTTP");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.onGetPatientResult(false, null);
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
