package com.example.application1.Visitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.application1.Constants;
import com.example.application1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.example.application1.Constants.URL_CMB_OPTIONS;

public class VisitorPage3 extends AppCompatActivity {

    Button btnNext;
    Spinner isappointed, department, appointmentwith;
    String data;
    ArrayList<String> isAppointedList = new ArrayList<>();
    ArrayList<String> departmentList = new ArrayList<>();
    ArrayList<String> appointmentList = new ArrayList<>();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.visitor_page_3);
        Intent intent = getIntent();
        data = intent.getStringExtra("contact_page");
        btnNext = (Button)findViewById(R.id.btnNext);
        isappointed = (Spinner)findViewById(R.id.cmbIsAppointed);
        department = (Spinner)findViewById(R.id.cmbDepartment);
        appointmentwith = (Spinner)findViewById(R.id.cmbIsAppointmentWith);
        setIsAppointed();
        setDepartment();
        setAppointmentWith();
    }

    public void onNext(View v){
        data += isappointed.getSelectedItem().toString()+"~"+department.getSelectedItem().toString()+"~"+appointmentwith.getSelectedItem().toString()+"~";
        Intent intent = new Intent(VisitorPage3.this, VisitorPage4.class);
        intent.putExtra("visitor_page_3", data);
        startActivity(intent);
    }


    public void setIsAppointed(){
        requestQueue =  Volley.newRequestQueue(VisitorPage3.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CMB_OPTIONS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("cmbOptions");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String item = jsonObject1.getString("cmbName");
                        isAppointedList.add(item);
                    }
                    isappointed.setAdapter(new ArrayAdapter<String>(VisitorPage3.this, android.R.layout.simple_spinner_dropdown_item, isAppointedList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pageid", "3");
                params.put("groupid", "1");
                return params;
            }
        };
//        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void setDepartment(){
        requestQueue =  Volley.newRequestQueue(VisitorPage3.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CMB_OPTIONS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("cmbOptions");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String item = jsonObject1.getString("cmbName");
                        departmentList.add(item);
                    }
                    department.setAdapter(new ArrayAdapter<String>(VisitorPage3.this, android.R.layout.simple_spinner_dropdown_item, departmentList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pageid", "3");
                params.put("groupid", "2");
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void setAppointmentWith(){
        requestQueue =  Volley.newRequestQueue(VisitorPage3.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_FACULTY_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("facultyList");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String item = jsonObject1.getString("facultyName");
                        appointmentList.add(item);
                    }
                    appointmentwith.setAdapter(new ArrayAdapter<String>(VisitorPage3.this, android.R.layout.simple_spinner_dropdown_item, appointmentList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("department", "COMPS");
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }


}
