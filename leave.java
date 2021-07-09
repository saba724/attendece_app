package com.example.attendence;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.LeaveResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class leave extends AppCompatActivity {

    TextView time1, time2;
    EditText reason;
    int year;
    int month;
    int day;
    Button leave_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        time1 = findViewById(R.id.tv2);
        time2 = findViewById(R.id.tv4);
        reason = findViewById(R.id.reason_et);
        leave_btn = findViewById(R.id.leave_btn);
        leave_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (time1.length() == 0) {
                    time1.setError("enter valid date");
                    time1.requestFocus();
                    return;
                }

                if (time2.length() == 0) {
                    time2.setError("enter date");
                    time2.requestFocus();
                    return;
                }
                if (reason.length() == 0) {
                    reason.setError("give reason");
                    reason.requestFocus();
                    return;
                }
                leaveApi();
            }
        });

        final Calendar calendar = Calendar.getInstance();
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //displaying calender
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(leave.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time1.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });

        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(leave.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time2.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void leaveApi() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<LeaveResponse> call = service.leave(time1.getText().toString().trim(), time2.getText().toString().trim(), reason.getText().toString().trim(),SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID));
        call.enqueue(new Callback<LeaveResponse>() {
            @Override
            public void onResponse(Call<LeaveResponse> call, Response<LeaveResponse> response) {

                // pbar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        Toast.makeText(leave.this, response.body().message, Toast.LENGTH_LONG).show();
                        // Intent intent = new Intent(leave.this, HomePage.class);
                        // SharedPreference.getInstance(getApplicationContext()).saveString(SharedPreference.USER_ID, username.getText().toString());
                        //startActivity(intent);
                        // finishAffinity();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LeaveResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                //pbar.setVisibility(View.GONE);

            }
        });
    }

}

//res=findViewById(R.id.tv6);

//automatic data diaplay
//        TextWatcher textWatcher=new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!time1.getText().toString().equals("") && !time2.getText().toString().equals(""))
//                {
//                    int temp1=Integer.parseInt(time1.getText().toString());
//                    int temp2=Integer.parseInt(time2.getText().toString());
//                    res.setText(String.valueOf(temp1 - temp2));
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        };
//        time1.addTextChangedListener(textWatcher);
//        time2.addTextChangedListener(textWatcher);