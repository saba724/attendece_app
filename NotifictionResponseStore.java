package com.example.attendence.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifictionResponseStore {
    @SerializedName("error")
    @Expose
    public String error;

    @SerializedName("message")
    @Expose
    public String message;
}
