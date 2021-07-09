package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class adminmgmt extends AppCompatActivity {
    Button in_out_admin,leave_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmgmt);
        in_out_admin=findViewById(R.id.in_out);
        leave_admin =findViewById(R.id.leavead);
        in_out_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(adminmgmt.this,employee_in_out.class);
                startActivity(intent);
            }
        });
        leave_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(adminmgmt.this,leavemngmt.class);
                startActivity(intent);
            }
        });
    }
}