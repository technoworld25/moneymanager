package com.apptechno.dailyprojectmanagment.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Project(

    @SerializedName("id")
    val projectId:String,

    @SerializedName("name")
    val projectName:String ,

    @SerializedName("poc")
    val poc:String="N.A.",

    @SerializedName("pocno")
    val pocNo:String="N.A.",

    @SerializedName("designation")
    val designation:String="N.A.",

    @SerializedName("architect")
    val architect:String="N.A.",

    @SerializedName("architectno")
    val architectNo:String="N.A.",

    @SerializedName("lead")
    val asignee:String="N.A.",

    @SerializedName("year")
     val year:String="N.A.",

    @SerializedName("address")
    val address:String="N.A.",

    @SerializedName("state")
    var state:String,

    @SerializedName("project_status")
    var status:String,

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
        parcel.readString().toString(),

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectId)
        parcel.writeString(projectName)
        parcel.writeString(address)
        parcel.writeString(designation)
        parcel.writeString(poc)
        parcel.writeString(pocNo)
        parcel.writeString(architect)
        parcel.writeString(architectNo)
        parcel.writeString(asignee)
        parcel.writeString(year)
        parcel.writeString(state)
        parcel.writeString(status)
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