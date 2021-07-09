package com.example.attendence;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.NotifictionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class notification extends AppCompatActivity {
    RecyclerView recyclerView;
    notify_adapter na;
    ArrayList<NotifictionResponse.notificationlist> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.recycle);
        notifyApi();
        show();
    }

    public void show() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
        na = new notify_adapter(list,this);
        recyclerView.setAdapter(na);

    }

    public void notifyApi() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<NotifictionResponse> call = service.notification(SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID));
        call.enqueue(new Callback<NotifictionResponse>() {
            @Override
            public void onResponse(Call<NotifictionResponse> call, Response<NotifictionResponse> response) {

                // pbar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {

                        list.clear();
                        list.addAll(0,response.body().notificationlist);
                        show();
                    }
                    catch (Exception e)
                    {
                        
                    }
                } else {
                    list.clear();
                    Toast.makeText(notification.this, "somthing went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NotifictionResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                //pbar.setVisibility(View.GONE);

            }
        });
    }

}