package com.example.attendence.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotifictionResponse {
    @SerializedName("notification")
    @Expose
    public List<NotifictionResponse.notificationlist> notificationlist;

    public class notificationlist
    {
        @SerializedName("title")
        @Expose
        public String title1;

        @SerializedName("description")
        @Expose
        public String description1;

//        @SerializedName("message")
//        @Expose
//        public String message1;

        @SerializedName("date_time_set")
        @Expose
        public String date_time_set1;

    }
}
