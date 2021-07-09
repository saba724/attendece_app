package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.emplyloginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class emplylogin extends AppCompatActivity {
    Button btnr,btnl;
    EditText username,password;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplylogin);
        btnl=findViewById(R.id.btnlog);
       // btnr=findViewById(R.id.btnreg);
        username=findViewById(R.id.usn);
        password=findViewById(R.id.emp_ps);

       // pbar = findViewById(R.id.loading);

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
               if (username.length() == 0) {
                    username.setError("enter name");
                    username.requestFocus();
                    return;
                }

               if (password.length() == 0) {
                    password.setError("enter password");
                    password.requestFocus();
                    return;
                }

               emplyloginApi();
            }
        });

//        btnr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(emplylogin.this, emplyreg.class);
//                startActivity(intent);
//            }
//        });
    }

    private void emplyloginApi() {
       // pbar.setVisibility(View.VISIBLE);
//        Toast.makeText(getApplicationContext(), "Cicked", Toast.LENGTH_SHORT).show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<emplyloginResponse> call = service.login(username.getText().toString().trim(), password.getText().toString().trim());
        call.enqueue(new Callback<emplyloginResponse>() {
            @Override
            public void onResponse(Call<emplyloginResponse> call, Response<emplyloginResponse> response) {

               // pbar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        Toast.makeText(emplylogin.this, response.body().message, Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(emplylogin.this, HomePage.class);

                       SharedPreference.getInstance(getApplicationContext()).saveString(SharedPreference.USER_ID, username.getText().toString());

                        startActivity(intent);

                        finishAffinity();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<emplyloginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                //pbar.setVisibility(View.GONE);

            }
        });
    }

}
