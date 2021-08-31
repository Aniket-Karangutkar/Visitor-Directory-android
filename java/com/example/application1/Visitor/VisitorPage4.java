package com.example.application1.Visitor;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application1.R;

import java.io.ByteArrayOutputStream;

public class VisitorPage4 extends AppCompatActivity {

    Button btnNext;
    ImageView img;
    String data;
    byte[] bytesImage;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.visitor_page_4);
        Intent intent = getIntent();
        data = intent.getStringExtra("visitor_page_3");
        btnNext = (Button)findViewById(R.id.btnNext);
        img = (ImageView)findViewById(R.id.imgArea);
    }

    public void setImage(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try{
            startActivityForResult(intent, 0);
        }catch (ActivityNotFoundException e){

        }
    }
    public void onNext(View v){
        Intent intent = new Intent(VisitorPage4.this, VisitorPage5.class);
        intent.putExtra("visitor_page_4", data);
//        intent.putExtra("image", bytesImage);
        startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestcode, int resultCode, Intent idata){
        super.onActivityResult(requestcode, resultCode, idata);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if(requestcode == 0 && resultCode == RESULT_OK) {
            img = this.findViewById(R.id.imgArea);
            Bitmap bm = (Bitmap) idata.getExtras().get("data");
            img.setImageBitmap(bm);
            bm.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
            bytesImage = byteArrayOutputStream.toByteArray();
            data += Base64.encodeToString(bytesImage, Base64.DEFAULT);
        }
    }

}
