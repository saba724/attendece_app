package com.example.attendence;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.chech_in_outResponse;
import com.example.attendence.data.check_entry_rep;
import com.example.attendence.data.check_out_rep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class check_in_out extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Button  btntime, btnsbmt, btnout;
    TextView time, date,out, entrydone;
    int mHour;
    int mMinute;

    String check_in_out_valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out);

        //btntime = findViewById(R.id.b);
        btnsbmt = findViewById(R.id.sbmt);
        time = findViewById(R.id.c_time);
        date = findViewById(R.id.c_date);
       //btnout=findViewById(R.id.btn_out);
        out=findViewById(R.id.out_time);

        entrydone = findViewById(R.id.text_entrydone);

        check_entry_api();

        //displaying automatic date format
        String dis_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        date.setText(dis_date);


        btnsbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_in_out_valid == "0"){
                    check_out_api();
                } else if (check_in_out_valid == "1") {
                    processApi();
                }
            }
        });
    }

    private void processApi() {
       // Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),time.getText().toString().trim(), Toast.LENGTH_LONG).show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<chech_in_outResponse> call = service.in_out(date.getText().toString().trim(), time.getText().toString().trim(),SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID));
        call.enqueue(new Callback<chech_in_outResponse>() {
            @Override
            public void onResponse(Call<chech_in_outResponse> call, Response<chech_in_outResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(emplyreg.this, emplylogin.class);
                        //startActivity(intent);
                    }

                }
            }
            @Override
            public void onFailure(Call<chech_in_outResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        time.setText("Hours:" + hourOfDay + " " + "minute:" + minute);
    }

    private void check_out_api(){
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<check_out_rep> call = service.out_entry(date.getText().toString().trim(), out.getText().toString().trim(),SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID));
        call.enqueue(new Callback<check_out_rep>() {
            @Override
            public void onResponse(Call<check_out_rep> call, Response<check_out_rep> response) {
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(emplyreg.this, emplylogin.class);
                        //startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<check_out_rep> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void check_entry_api(){
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<check_entry_rep> call = service.check_entry(SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID));
        call.enqueue(new Callback<check_entry_rep>() {
            @Override
            public void onResponse(Call<check_entry_rep> call, Response<check_entry_rep> response) {
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("1")) {
//                        btntime.setVisibility(View.VISIBLE);
                        check_in_out_valid = "1";
                        time.setText(response.body().curr_time);
                    } else if(response.body().error.equalsIgnoreCase("0")) {
//                        btnout.setVisibility(View.VISIBLE);
                        check_in_out_valid = "0";
                        out.setText(response.body().curr_time);
                    } else {
                        entrydone.setVisibility(View.VISIBLE);
                        check_in_out_valid = "2";
                        btnsbmt.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onFailure(Call<check_entry_rep> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}




//        btntime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                DialogFragment dialogFragment = new Intimefragment();
////                dialogFragment.show(getSupportFragmentManager(), "time picker");
//
//                final Calendar c = Calendar.getInstance();
//                mHour = c.get(Calendar.HOUR_OF_DAY);
//                mMinute = c.get(Calendar.MINUTE);
//
//// Launch Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(check_in_out.this, new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                                time.setText(hourOfDay + ":" + minute);
//                            }
//                        }, mHour, mMinute, false);
//                timePickerDialog.show();
//            }
//        });
//        btnout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                DialogFragment dialogFragment = new OuttimeFragment();
////                dialogFragment.show(getSupportFragmentManager(), "time picker");
//
//                final Calendar c = Calendar.getInstance();
//                mHour = c.get(Calendar.HOUR_OF_DAY);
//                mMinute = c.get(Calendar.MINUTE);
//
//// Launch Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(check_in_out.this, new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//                                out.setText(hourOfDay + ":" + minute);
//
////                                Toast.makeText(getApplicationContext(),hourOfDay + ":" + minute,Toast.LENGTH_LONG).show();
//                            }
//                        }, mHour, mMinute, false);
//                timePickerDialog.show();
//            }
//        });