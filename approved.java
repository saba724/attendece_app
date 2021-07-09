package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.app_rej_rep;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class approved extends AppCompatActivity {

    String leave_status, leave_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved);
        Intent i = new Intent();

        leave_status = i.getStringExtra("lv_status");
        leave_id = i.getStringExtra("lv_id");
//        if(leave_status == "1"){
//            Leave_app_rej_api("1");
//        } else {
//            Leave_app_rej_api("2");
//        }

        Leave_app_rej_api();

    }

    private void Leave_app_rej_api() {
        Toast.makeText(getApplicationContext(),leave_id+" - "+leave_status,Toast.LENGTH_LONG).show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<app_rej_rep> call = service.app_rej(leave_id, leave_status);
        call.enqueue(new Callback<app_rej_rep>() {
            @Override
            public void onResponse(Call<app_rej_rep> call, Response<app_rej_rep> response) {
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
//
                       // Toast.makeText(getApplicationContext(),response.body().message,Toast.LENGTH_LONG).show();
//                        Toast.makeText(approved.this,"hi",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<app_rej_rep> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}