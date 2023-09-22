package com.apptechno.dailyprojectmanagment.model

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("username")
    val username:String,
    @SerializedName("password")
    val password:String) {
}

data class User(

    @SerializedName("username")
    val username:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("password")
    val password:String){

}

