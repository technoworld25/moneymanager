package com.apptechno.dailyprojectmanagment.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TaskDetails(@SerializedName("taskid")
val taskid:String,

@SerializedName("assigner")
val assigner:String,

@SerializedName("taskname")
val taskname:String,

@SerializedName("description")
val description:String,

@SerializedName("taskstate")
val taskstate : String,

@SerializedName("asignee")
val assignee: String,

@SerializedName("projectid")
val projectid:Int,

@SerializedName("id")
val id:Int,

@SerializedName("name")
val name:String,

@SerializedName("client")
val client:String,

@SerializedName("address")
val address:String,

@SerializedName("phone")
val phone:String,

@SerializedName("poc")
val poc:String,

@SerializedName("pocno")
val pocNo:String,

@SerializedName("architect")
val architect:String,

@SerializedName("architectno")
val architectNo:String,

@SerializedName("lead")
val lead:String,

@SerializedName("year")
val year:String,

@SerializedName("state")
val projectState:String) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(taskid)
        parcel.writeString(assigner)
        parcel.writeString(taskname)
        parcel.writeString(description)
        parcel.writeString(taskstate)
        parcel.writeString(assignee)
        parcel.writeInt(projectid)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(client)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(poc)
        parcel.writeString(pocNo)
        parcel.writeString(architect)
        parcel.writeString(architectNo)
        parcel.writeString(lead)
        parcel.writeString(year)
        parcel.writeString(projectState)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskDetails> {
        override fun createFromParcel(parcel: Parcel): TaskDetails {
            return TaskDetails(parcel)
        }

        override fun newArray(size: Int): Array<TaskDetails?> {
            return arrayOfNulls(size)
        }
    }

}