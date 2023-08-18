package com.apptechno.dailyprojectmanagment.model

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("assigner")
    val assigner:String,

    @SerializedName("taskname")
    val taskname:String,

    @SerializedName("description")
    val description:String,

    @SerializedName("state")
    val state : String,

    @SerializedName("assignee")
    val assignee: String,

    @SerializedName("projectid")
    val projectid:Int) {
    
}
