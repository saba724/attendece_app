package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID).equals("")){
            startActivity(new Intent(getApplicationContext(), HomePage.class));
            finishAffinity();
        }

        btn1=findViewById(R.id.admin);
        btn2=findViewById(R.id.emply);
       // btn3=findViewById(R.id.hp);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, adminlogin.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, emplylogin.class);
                startActivity(intent);
            }
        });

//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this, HomePage.class);
//                startActivity(intent);
//            }
//        });


    }
}