package com.example.attendence.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class regemplyinfoResponse {
    @SerializedName("EmployeeInfo")
    @Expose
    public List<regemplyinfoResponse.emplydetails> emplydetails;


    public class emplydetails {
        @SerializedName("name")
        @Expose
        public String name1;

        @SerializedName("email")
        @Expose
        public String emai1;

        @SerializedName("phoneno")
        @Expose
        public String phoneno1;
    }
}

