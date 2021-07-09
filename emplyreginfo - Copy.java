package com.example.attendence;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.regemplyinfoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class emplyreginfo extends AppCompatActivity {
    RecyclerView recyclerView;
    List<regemplyinfoResponse.emplydetails> list=new ArrayList<>();
    regemplyinfo_adapater regemplyinfo_adapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplyreginfo);
        recyclerView=findViewById(R.id.recycleemplyinfo);
        show();
        employeeInfoApi();

    }

    private void show() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        regemplyinfo_adapater=new regemplyinfo_adapater((ArrayList<regemplyinfoResponse.emplydetails>) list,this);
        recyclerView.setAdapter(regemplyinfo_adapater);
    }
    private void employeeInfoApi() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<regemplyinfoResponse> call = service.emplyinfo();
        call.enqueue(new Callback<regemplyinfoResponse>() {
            @Override
            public void onResponse(Call<regemplyinfoResponse> call, Response<regemplyinfoResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        list.clear();
                        list.addAll(response.body().emplydetails);

//                    list.addAll()
                        show();

                    }
                    catch (Exception e){

                    }

                } else {
                    list.clear();
                    Toast.makeText(emplyreginfo.this, "something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<regemplyinfoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}