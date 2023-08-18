package com.apptechno.dailyprojectmanagment.model

import com.google.gson.annotations.SerializedName

data class Project(

    @SerializedName("name")
    val projectName:String,

    @SerializedName("client")
    val clientName : String,

    @SerializedName("address")
    val address:String,

    @SerializedName("phone")
    val contactNo:String,

    @SerializedName("poc")
    val poc:String,

    @SerializedName("pocNo")
    val pocNo:String,

    @SerializedName("architect")
    val architect:String,

    @SerializedName("architectNo")
    val architectNo:String,

    @SerializedName("asignee")
    val asignee:String,

    @SerializedName("year")
     val year:String,

    @SerializedName("state")
    val state:String
    ) {
}