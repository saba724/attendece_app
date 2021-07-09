package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class description extends AppCompatActivity {
    TextView des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        des=findViewById(R.id.desctv);
        Intent intent=getIntent();
      des.setText(intent.getStringExtra("description"));

    }
}