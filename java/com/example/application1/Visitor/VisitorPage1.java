package com.example.application1.Visitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.application1.Constants;
import com.example.application1.R;
import com.example.application1.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.example.application1.Constants.URL_CMB_OPTIONS;

public class VisitorPage1 extends AppCompatActivity {
    Button btnNext;
    EditText visitorname, noofpoepletogether;
    Spinner visitingreason;
    PopulateSpinner populateSpinner;
    ArrayList<String> itemList = new ArrayList<>();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.visitor_page_1);

        btnNext = (Button)findViewById(R.id.btnNext);
        visitorname = (EditText)findViewById(R.id.txtVisitorName);
        noofpoepletogether = (EditText)findViewById(R.id.txtNoOfPeopleTogether);
        visitingreason = (Spinner)findViewById(R.id.cmbVisitingReason);

        setSpinnerItems();
    }

    public void onNext(View v){
        String data = visitorname.getText().toString()+"~"+visitingreason.getSelectedItem().toString()+"~"+noofpoepletogether.getText().toString()+"~";
        Intent intent = new Intent(VisitorPage1.this, VisitorPage2.class);
        intent.putExtra("visitor_page_1", data);
        startActivity(intent);
    }


    public void setSpinnerItems() {
    requestQueue =  Volley.newRequestQueue(VisitorPage1.this);
    StringRequest stringRequest = new StringRequest(POST, URL_CMB_OPTIONS, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("cmbOptions");
                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String item = jsonObject1.getString("cmbName");
                    itemList.add(item) ;
                }
                visitingreason.setAdapter(new ArrayAdapter<String>(VisitorPage1.this, android.R.layout.simple_spinner_dropdown_item, itemList));
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
            params.put("pageid", "1");
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
}
