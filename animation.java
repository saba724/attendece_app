package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class animation extends AppCompatActivity {
    public static int splash=4000;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_animation);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent=new Intent(animation.this,MainActivity.class);
                 startActivity(intent);
                 finishAffinity();


      }
         },splash);
    }

}