package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class adminhp extends AppCompatActivity {
    Button btnsend,btnmngmt,btnaddemployee,emplyreginfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhp);
        btnsend=findViewById(R.id.send);
        btnmngmt=findViewById(R.id.mgmt);
        btnaddemployee=findViewById(R.id.addmgmt);
        emplyreginfo=findViewById(R.id.emplyreginfo);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminhp.this,adminsend.class);
                startActivity(intent);
            }
        });
        btnmngmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminhp.this,adminmgmt.class);
                startActivity(intent);
            }
        });

        btnaddemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminhp.this,emplyreg.class);
                startActivity(intent);
            }
        });
        emplyreginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminhp.this,emplyreginfo.class);
                startActivity(intent);
            }
        });
    }
}