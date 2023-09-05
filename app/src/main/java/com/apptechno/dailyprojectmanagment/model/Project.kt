package com.apptechno.dailyprojectmanagment.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Project(

    @SerializedName("id")
    val projectId:String,

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

    @SerializedName("pocno")
    val pocNo:String,

    @SerializedName("architect")
    val architect:String,

    @SerializedName("architectno")
    val architectNo:String,

    @SerializedName("lead")
    val asignee:String,

    @SerializedName("year")
     val year:String,

    @SerializedName("state")
    var state:String
    ) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectId)
        parcel.writeString(projectName)
        parcel.writeString(clientName)
        parcel.writeString(address)
        parcel.writeString(contactNo)
        parcel.writeString(poc)
        parcel.writeString(pocNo)
        parcel.writeString(architect)
        parcel.writeString(architectNo)
        parcel.writeString(asignee)
        parcel.writeString(year)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Project> {
        override fun createFromParcel(parcel: Parcel): Project {
            return Project(parcel)
        }

        override fun newArray(size: Int): Array<Project?> {
            return arrayOfNulls(size)
        }
    }

}