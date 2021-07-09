package com.example.attendence.api;

import com.example.attendence.data.ImageResponse;
import com.example.attendence.data.LeaveResponse;
import com.example.attendence.data.NotifictionResponse;
import com.example.attendence.data.NotifictionResponseStore;
import com.example.attendence.data.ProfileResponse;
import com.example.attendence.data.app_rej_rep;
import com.example.attendence.data.chech_in_outResponse;
import com.example.attendence.data.check_entry_rep;
import com.example.attendence.data.check_out_rep;
import com.example.attendence.data.emplyloginResponse;
import com.example.attendence.data.emplyregisterResponse;
import com.example.attendence.data.in_out_adminResponse;
import com.example.attendence.data.leavemgmtResponse;
import com.example.attendence.data.regemplyinfoResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("emplylogin.php")
    Call<emplyloginResponse> login(@Query("username") String username,
                                   @Query("password")String password);

    @POST("emplyregister.php")
    Call<emplyregisterResponse> register(@Query("name") String name,
                                         @Query("email") String email,
                                         @Query("password")String password,
                                        @Query("phoneno") String phoneno,
                                         @Query("designation")String designation,
                                         @Query("city") String city,
                                         @Query("address")String address);

    @POST("in_out.php")
    Call<chech_in_outResponse> in_out(@Query("date") String date,
                                      @Query("login") String login,
                                      @Query("username") String username);

    @POST("in_out.php")
    Call<check_out_rep> out_entry(@Query("date") String date,
                                  @Query("logout") String logout,
                                  @Query("username") String username);

    @POST("leave.php")
    Call<LeaveResponse> leave(@Query("f_date") String f_date,
                              @Query("t_date") String t_date,
                              @Query("reason") String reason,
                                @Query("username") String username);

    @POST("profilefetch.php")
    Call<ProfileResponse> profile(@Query("username") String username);

    @POST("imagestore.php")
    Call<ImageResponse> image(@Query("url") String url);

    @POST("notificationstore.php")//string
    Call<NotifictionResponseStore> notification_store(@Query("title") String title,
                                                      @Query("desc")String desc);

    @POST("notifyfetch.php")//for reponse we are sendig request
    Call<NotifictionResponse> notification(@Query("uid") String uid);

    @POST("inout_fetch.php")//for reponse we are sendig request
    Call<in_out_adminResponse> in_out_fetch(@Query("name") String name,
                                            @Query("check_in")String checkin,
                                            @Query("check_out")String checkout);

    @POST("check_login_out.php")
    Call<check_entry_rep> check_entry(@Query("username") String username);

    @POST("leavemgmt_fetch.php")//for reponse we are sendig request
    Call<leavemgmtResponse> leave_fetch(@Query("name") String name,
                                        @Query("start_date")String start_date,
                                        @Query("end_date")String end_date,
                                        @Query("reason")String reason);

    @POST("app_rej_leave.php")
    Call<app_rej_rep> app_rej(@Query("leave_id") String leave_id,
                              @Query("leave_status") String leave_status);

    @POST("regemplyinfo.php")//for reponse we are sendig request
    Call<regemplyinfoResponse> emplyinfo();

}




