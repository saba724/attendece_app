package com.example.attendence;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.ImageResponse;
import com.example.attendence.data.ProfileResponse;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {
    TextView name, email, password, phonno, designation, city, address;
    Button logout;
    String t_image="https://images.app.goo.gl/BqJMpyhXqqzPCBew7";
    String user_id;
    //for gallery code declaring
   private CircleImageView proimg;
   private static final int Pick_img=1;
   Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileApi();
        imageApi();
        name = findViewById(R.id.name_tv);
        email = findViewById(R.id.email_tv);
        //password = findViewById(R.id.password_tv);
        phonno = findViewById(R.id.phonno_tv);
        designation = findViewById(R.id.designation_tv);
        city = findViewById(R.id.city_tv);
        address = findViewById(R.id.address_tv);
        logout = findViewById(R.id.logout);
        proimg=findViewById(R.id.proimg);

        //code for adding the image from gallery
        proimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),Pick_img);
            }
        });

        //code for logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(profile.this);
                builder.setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                profile.super.onBackPressed(); //coming out from the activity like exit

                                SharedPreference.getInstance(getApplicationContext()).logoutUser(getApplicationContext());
                            }
                        })
                        .setNegativeButton("Cancel", null);
                AlertDialog alert = builder.create();
                alert.show();
                //end
            }
        });
    }

    private void imageApi() {
        ApiInterface service= ApiClient.getClient().create(ApiInterface.class);
        Call<ImageResponse> call=service.image(t_image);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(emplyreg.this, emplylogin.class);
//                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
    //contineuing gallery image code by pressing ctrl+o

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Pick_img && resultCode==RESULT_OK)
        {
            imageUri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                proimg.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    //endf
    public void profileApi() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResponse> call = service.profile(SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID));
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    // if (response.body().name.equalsIgnoreCase(SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID)) ){
                    // if (response.body().error.equalsIgnoreCase("0")) {
                    name.setText(response.body().name);
                    email.setText(response.body().email);
                    //password.setText(response.body().password);
                    phonno.setText(response.body().phoneno);
                    designation.setText(response.body().designation);
                    city.setText(response.body().city);
                    address.setText(response.body().address);


//                    Toast.makeText(getApplicationContext(), SharedPreference.getInstance(getApplicationContext())
//                            .getString(SharedPreference.USER_ID), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), response.body().name, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}




