package com.example.application1.Visitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application1.R;

public class ContactPage extends AppCompatActivity {

    Button btnNext;
    EditText contact;
    String data;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_contact_page);
        Intent intent = getIntent();
        data = intent.getStringExtra("visitor_page_2");
        btnNext = (Button)findViewById(R.id.btnNext);
        contact = (EditText)findViewById(R.id.txtContact1);
    }

    public void onNext(View v){
        data += contact.getText().toString()+"~";
        Intent intent = new Intent(ContactPage.this, VisitorPage3.class);
        intent.putExtra("contact_page",data);
        startActivity(intent);
    }
}
