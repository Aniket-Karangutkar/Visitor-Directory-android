package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.application1.Visitor.ViewVisitor;
import com.example.application1.Visitor.VisitorPage1;
import com.example.application1.Visitor.VisitorPage5;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void addVisitor(View view){
        Intent intent = new Intent(this, VisitorPage1.class);
        startActivity(intent);
    }

    public void viewVisitor(View view){
        Intent intent = new Intent(this, ViewVisitor.class);
        startActivity(intent);
    }

    public void logout(View v){
        SharedPrefManager.getInstance(this).logout();
        finish();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}