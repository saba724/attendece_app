package com.example.attendence.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class in_out_adminResponse {

    @SerializedName("Employee")
    @Expose
    public  List<in_out_adminResponse.EmployeeList> employeeLists;

    public class EmployeeList {


        @SerializedName("username")
        @Expose
        public String username1;

        @SerializedName("checkin")
        @Expose
        public String checkin1;

        @SerializedName("checkout")
        @Expose
        public String checkout1;

        @SerializedName("date_time")
        @Expose
        public String date_time1;


    }
}
