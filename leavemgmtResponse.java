package com.example.attendence.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class leavemgmtResponse {
    @SerializedName("Leave")
    @Expose
    public List<leavemgmtResponse.Leavelist> leavelists;

    public class Leavelist {

        @SerializedName("leave_id")
        @Expose
        public String leave_id;

        @SerializedName("username")
        @Expose
        public String username_leave;

        @SerializedName("start_date")
        @Expose
        public String start_date1;

        @SerializedName("end_date")
        @Expose
        public String end_date1;

        @SerializedName("reason")
        @Expose
        public String reason1;

//        @SerializedName("date_time_leave")
//        @Expose
//        public String date_time_leave1;

//        @SerializedName("message")
//        @Expose
//        public String message;
    }
}
