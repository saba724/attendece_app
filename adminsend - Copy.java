package com.example.attendence;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.NotifictionResponseStore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminsend extends AppCompatActivity {
    private static final String TAG = "adminsend";
    EditText title, desc;
    Button send;
    //ArrayList<NotifictionResponse.notificationlist> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsend);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.des);
        send = findViewById(R.id.sendad);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.length() == 0) {
                    title.setError("enter title");
                    title.requestFocus();
                    return;
                }

                if (desc.length() == 0) {
                    desc.setError("enter description");
                    desc.requestFocus();
                    return;
                }
                sendApi();
                //getToken();
            }
        });
    }

    private void sendApi() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<NotifictionResponseStore> call = service.notification_store(title.getText().toString().trim(), desc.getText().toString().trim());
        call.enqueue(new Callback<NotifictionResponseStore>() {
            @Override
            public void onResponse(Call<NotifictionResponseStore> call, Response<NotifictionResponseStore> response) {
                // pbar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        Toast.makeText(adminsend.this, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NotifictionResponseStore> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getToken() {
        // Log.i("displaying msg","hi");
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                //Log.i("displaying msg","hi");
                if (task.isSuccessful()) {
                    Log.d(TAG, "oncomplete failed to get the token");
                }
                String token = task.getResult();
                Log.d(TAG, "oncomplete" + token);

            }
        });
    }
}
