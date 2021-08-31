package com.example.application1.Visitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.application1.Constants;
import com.example.application1.MainActivity;
import com.example.application1.R;
import com.example.application1.RequestHandler;
import com.example.application1.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import static com.android.volley.Request.Method.POST;


public class VisitorPage5 extends AppCompatActivity {

    Button btnNext;
    String data, results;
    TextView result;
    String guardName = SharedPrefManager.getInstance(VisitorPage5.this).getKeyUsername();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.visitor_page_5);
        Intent intent = getIntent();
        data = intent.getStringExtra("visitor_page_4");
        btnNext = (Button)findViewById(R.id.btnNext);
        result = (TextView)findViewById(R.id.lblRecordStatus);
        sendData();
    }



    public void onNext(View v){
        finish();
        Intent intent = new Intent(VisitorPage5.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VisitorPage5.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendData(){
        StringRequest stringRequest = new StringRequest(POST, Constants.URL_DATA_UPLOAD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    result.setText(Response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String[] sdata = data.split("~");
                Map<String,String> params = new HashMap<>();
                params.put("filename",guardName);
                params.put("visitorname",sdata[0]);
                params.put("visitreason",sdata[1]);
                params.put("noofpeopletogether",sdata[2]);
                params.put("bldgname",sdata[3]);
                params.put("state",sdata[4]);
                params.put("city",sdata[5]);
                params.put("contact", sdata[6]);
                params.put("isappointed",sdata[7]);
                params.put("department",sdata[8]);
                params.put("appointmentwith",sdata[9]);
                params.put("image",sdata[10]);
                params.put("visitdate", simpleDateFormat.format(new Date()));
                params.put("visittime", simpleTimeFormat.format(new Date()));
                return params;
            }
        };
        RequestHandler.getInstance(VisitorPage5.this).addToRequestQueue(stringRequest);
    }
}
