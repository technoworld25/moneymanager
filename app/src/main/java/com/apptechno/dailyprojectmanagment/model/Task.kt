package com.apptechno.dailyprojectmanagment.model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName


data class Task(
    @SerializedName("taskid")
    val taskid:String,

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


data class TaskResponse(

    @SerializedName("taskid")
    val taskid:String,

    @SerializedName("assigner")
    val assigner:String,

    @SerializedName("taskname")
    val taskname:String,

    @SerializedName("description")
    val description:String,

    @SerializedName("taskstate")
    val state : String,

    @SerializedName("asignee")
    val assignee: String,

    @SerializedName("projectid")
    val projectid:Int,

    @SerializedName("id")
    val id:Int,

    @SerializedName("name")
    val name:String,

    @SerializedName("client")
    val client:String):Parcelable
{
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
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(taskid)
        parcel.writeString(assigner)
        parcel.writeString(taskname)
        parcel.writeString(description)
        parcel.writeString(state)
        parcel.writeString(assignee)
        parcel.writeInt(projectid)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(client)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskResponse> {
        override fun createFromParcel(parcel: Parcel): TaskResponse {
            return TaskResponse(parcel)
        }

        override fun newArray(size: Int): Array<TaskResponse?> {
            return arrayOfNulls(size)
        }
    }


}

data class GetTaskRequest(

    @SerializedName("projectName")
    val projectName: String
)

