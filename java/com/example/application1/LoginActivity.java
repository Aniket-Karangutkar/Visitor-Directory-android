package com.example.application1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.application1.Constants;
import com.example.application1.R;
import com.example.application1.RequestHandler;
import com.example.application1.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity  extends AppCompatActivity{
    private ProgressDialog progressDialog;

    Button btnlogin;
    EditText txtusername, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        btnlogin = (Button) findViewById(R.id.btnLogin);
        txtusername = (EditText) findViewById(R.id.txtUsername);
        txtpassword = (EditText) findViewById(R.id.txtPassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }


    public void guardLogin(View v){
        final String username = txtusername.getText().toString().trim();
        final String password = txtpassword.getText().toString().trim();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                response -> {
                    progressDialog.dismiss();
                    JSONObject obj = null;
                    try {
                        obj = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (obj != null) {
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .guardLogin(
                                                obj.getInt("id"),
                                                obj.getString(("username"))
                                        );
                                Toast.makeText(getApplicationContext(),"User successfully logged in",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error please check username and password",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
