package com.example.attendence.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("error")
    @Expose
    public String error;

    @SerializedName("message")
    @Expose
    public String message;
  //  public class ProfileModel{
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("email")
    @Expose
    public String email;

//    @SerializedName("password")
//    @Expose
//    public String password;

    @SerializedName("phoneno")
    @Expose
    public String phoneno;

    @SerializedName("designation")
    @Expose
    public String designation;

    @SerializedName("city")
    @Expose
    public String city;

    @SerializedName("address")
    @Expose
    public String address;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public String getPhoneno() {
//            return phoneno;
//        }
//
//        public void setPhoneno(String phoneno) {
//            this.phoneno = phoneno;
//        }
//
//        public String getDesignation() {
//            return designation;
//        }
//
//        public void setDesignation(String designation) {
//            this.designation = designation;
//        }
//
//        public String getCity() {
//            return city;
//        }
//
//        public void setCity(String city) {
//            this.city = city;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//    @SerializedName("name")
//    @Expose
//    public  String name;
    }
//}
