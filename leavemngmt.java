package com.example.attendence;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.leavemgmtResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class leavemngmt extends AppCompatActivity {
    RecyclerView recyclerView;
    List<leavemgmtResponse.Leavelist> list=new ArrayList<>();
    leave_adapter leave_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leavemngmt);
        recyclerView=findViewById(R.id.recycle_leave);
        leavemngmtApi();
        show();
    }
    private void show() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        leave_adapter=new leave_adapter(list,this);
        recyclerView.setAdapter(leave_adapter);
    }

    private void leavemngmtApi() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<leavemgmtResponse> call = service.leave_fetch("", "", "", "");
        call.enqueue(new Callback<leavemgmtResponse>() {
            @Override
            public void onResponse(Call<leavemgmtResponse> call, Response<leavemgmtResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        list.clear();
                        list.addAll(0,response.body().leavelists);
                        show();

                    }
                    catch (Exception e){
                    }
                } else {
                    list.clear();
                    Toast.makeText(leavemngmt.this, "something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<leavemgmtResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}