package com.example.attendence.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class check_entry_rep {
    @SerializedName("error")
    @Expose
    public  String error;

    @SerializedName("curr_time")
    @Expose
    public String curr_time;
}
