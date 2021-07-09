package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.emplyregisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class emplyreg extends AppCompatActivity {
    EditText name,email,password,phoneno,designation,city,address;
    Button regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplyreg);
        name=findViewById(R.id.regname);
        email=findViewById(R.id.regmail);
        password=findViewById(R.id.regpass);
        phoneno=findViewById(R.id.phoneno);
        designation=findViewById(R.id.designation);
        city=findViewById(R.id.city);
        address=findViewById(R.id.address);
        regbtn=findViewById(R.id.subbtnreg);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0) {
                    name.setError("enter name");
                } else if (email.length() == 0) {
                    email.setError("enter email");
                } else if (password.length() == 0) {
                    password.setError("enter password");
                } else if (phoneno.length() == 0) {
                    phoneno.setError("enter phoneno");
                }else if (designation.length() == 0) {
                    designation.setError("enter designation");
                }else if (city.length() == 0) {
                    city.setError("enter city");
                }else if (address.length() == 0) {
                    address.setError("enter address");
                }
                else {
                    emplyregisterApi();
                }
            }
        });
    }

    private void emplyregisterApi() {
        ApiInterface service= ApiClient.getClient().create(ApiInterface.class);
        Call<emplyregisterResponse> call=service.register(name.getText().toString().trim(), email.getText().toString().trim(),password.getText().toString().trim(),phoneno.getText().toString().trim(),designation.getText().toString().trim(),
                    city.getText().toString().trim(),address.getText().toString().trim());
        call.enqueue(new Callback<emplyregisterResponse>() {
            @Override
            public void onResponse(Call<emplyregisterResponse> call, Response<emplyregisterResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(emplyreg.this, emplylogin.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<emplyregisterResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}