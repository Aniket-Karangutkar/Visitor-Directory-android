package com.example.application1.Visitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
import com.example.application1.LoginActivity;
import com.example.application1.MainActivity;
import com.example.application1.RequestHandler;
import com.example.application1.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.example.application1.Constants.URL_CMB_OPTIONS;

public class PopulateSpinner {
//    public ArrayList<String> itemList;
//    RequestQueue requestQueue;
//    public String populateSpinner(Context context, final String pageid, final String groupid) {
//        requestQueue =  Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(POST, URL_CMB_OPTIONS, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("cmbOptions");
//                    for(int i=0; i<jsonArray.length(); i++){
//                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                        String item = jsonObject1.getString("cmbName");
//                        itemList.add(item);
//                    }
//                    visitingreason.setAdapter(new ArrayAdapter<String>(VisitorPage1.this, android.R.layout.simple_spinner_dropdown_item, itemList));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("pageid", "1");
//                params.put("groupid", "1");
//                return params;
//            }
//        };
////        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
//        int socketTimeout = 30000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
//        return itemList;
//    }
}
