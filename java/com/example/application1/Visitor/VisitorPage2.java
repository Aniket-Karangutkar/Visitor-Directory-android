package com.example.application1.Visitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class VisitorPage2 extends AppCompatActivity {

    Button btnNext;
    EditText bldgname;
    Spinner state,city;
    String data;
    ArrayList<String> stateList = new ArrayList<>();
    ArrayList<String> cityList = new ArrayList<>();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.visitor_page_2);
        Intent intent = getIntent();
        data = intent.getStringExtra("visitor_page_1");
        btnNext = (Button)findViewById(R.id.btnNext);
        bldgname = (EditText)findViewById(R.id.txtBldgName);
        state = (Spinner)findViewById(R.id.cmbState);
        city = (Spinner)findViewById(R.id.cmbCity);


        setStateItem();
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 14:
                        setCityItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void onNext(View v){
        data += bldgname.getText().toString()+"~"+state.getSelectedItem().toString()+"~"+city.getSelectedItem().toString()+"~";
        Intent intent = new Intent(VisitorPage2.this, ContactPage.class);
        intent.putExtra("visitor_page_2",data);
        startActivity(intent);
    }

    public void setStateItem(){
        requestQueue =  Volley.newRequestQueue(VisitorPage2.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_STATE_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("stateList");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String item = jsonObject1.getString("stateName");
                        stateList.add(item);
                    }
                    state.setAdapter(new ArrayAdapter<String>(VisitorPage2.this, android.R.layout.simple_spinner_dropdown_item, stateList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }


    public void setCityItem(){
        requestQueue =  Volley.newRequestQueue(VisitorPage2.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CITY_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("cityList");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String item = jsonObject1.getString("cityName");
                        cityList.add(item);
                    }
                    city.setAdapter(new ArrayAdapter<String>(VisitorPage2.this, android.R.layout.simple_spinner_dropdown_item, cityList));
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
                params.put("state", "15");
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
}
