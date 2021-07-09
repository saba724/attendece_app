package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class adminlogin extends AppCompatActivity {
    EditText email,password;
    Button adlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);


        email=findViewById(R.id.usn);
        password=findViewById(R.id.emp_ps);
        adlg=findViewById(R.id.btnlog);
        adlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=email.getText().toString();
                String passwd=password.getText().toString();
                if (username.equals("admin") && passwd.equals("123"))
                {
                    Toast.makeText(adminlogin.this,"login successfull",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(adminlogin.this, adminhp.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(adminlogin.this,"login unsuccessfull",Toast.LENGTH_LONG).show();

                }
                if (email.length() == 0) {
                    email.setError("enter email");
                    email.requestFocus();
                    return;
                }

                if (password.length() == 0) {
                    password.setError("enter password");
                    password.requestFocus();
                    return;
                }
            }
        });

    }
}