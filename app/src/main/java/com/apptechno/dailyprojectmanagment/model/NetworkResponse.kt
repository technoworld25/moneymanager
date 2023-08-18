package com.apptechno.dailyprojectmanagment.model

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

class NetworkResponse<T>(
    @SerializedName("status")
    val status: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: T) {
}