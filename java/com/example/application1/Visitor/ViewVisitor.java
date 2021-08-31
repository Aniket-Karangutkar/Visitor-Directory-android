package com.example.application1.Visitor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.application1.Constants;
import com.example.application1.R;
import com.example.application1.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.example.application1.Constants.URL_CMB_OPTIONS;
import static com.example.application1.Constants.URL_VIEW_VISITOR;

public class ViewVisitor extends AppCompatActivity {
    String  format = "yyyy-MM-dd";
    String guardName = SharedPrefManager.getInstance(ViewVisitor.this).getKeyUsername();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    RecyclerView recyclerView;
    RecyclerView.Adapter visitorAdapter;
    RequestQueue requestQueue;
    ArrayList<VisitorDetails> visitorList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visitor);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewVisitors();
    }


    public void viewVisitors(){
        requestQueue =  Volley.newRequestQueue(ViewVisitor.this);
        StringRequest stringRequest = new StringRequest(POST, URL_VIEW_VISITOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("visitorsList");
                    for(int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String visitorName = jsonObject1.getString("visitorname");
                        String visitorImg = jsonObject1.getString("image");
                        String visitorDate = jsonObject1.getString("visitdate");
                        Log.e("UPLOAD URL", visitorImg);
                        VisitorDetails visitorDetails = new VisitorDetails(visitorName, Constants.IMG_URL+visitorImg, visitorDate);

                        visitorList.add(visitorDetails) ;
                    }
//                    visitingreason.setAdapter(new ArrayAdapter<String>(VisitorPage1.this, android.R.layout.simple_spinner_dropdown_item, itemList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                visitorAdapter = new VisitorAdapter(ViewVisitor.this, visitorList);
                recyclerView.setAdapter(visitorAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("guardname", guardName);
                params.put("date", simpleDateFormat.format(new Date()));
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
