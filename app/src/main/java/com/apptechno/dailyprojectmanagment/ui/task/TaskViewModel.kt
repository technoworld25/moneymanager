package com.apptechno.dailyprojectmanagment.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptechno.dailyprojectmanagment.model.*
import com.apptechno.dailyprojectmanagment.network.GetDataService
import com.apptechno.dailyprojectmanagment.network.RetrofitClientInstance
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    val taskResponse : LiveData<NetworkResponse<Task>> =MutableLiveData()
    val tasks : LiveData<NetworkResponse<List<TaskResponse>>> =MutableLiveData()
    val updateTasksResponse : LiveData<NetworkResponse<Task>> =MutableLiveData()
    val assignedTasks : LiveData<NetworkResponse<List<TaskResponse>>> =MutableLiveData()
    val pendingTasks : LiveData<NetworkResponse<List<TaskDetails>>> =MutableLiveData()

    fun onSaveTaskClicked(taskDetails: Task){
        val retrofitService = RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)
        viewModelScope.launch {
            taskResponse as MutableLiveData
            val result = retrofitService?.addTask(taskDetails)
            if(result!=null && result.isSuccessful){
                taskResponse.value = result.body()

            }

        }

    }
    fun getTasks(projectName:String){

        val retrofitService = RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)
        tasks as MutableLiveData

        viewModelScope.launch {

            val result = retrofitService?.getTasks(GetTaskRequest(projectName))
            if(result!=null && result.isSuccessful){

                tasks.value = result.body()
            }
        }
    }


    fun updateTaskClicked(projectDetails: Task) {
        val retrofitService =
            RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)

        viewModelScope.launch {
            updateTasksResponse as MutableLiveData
            val result = retrofitService?.updateTask(projectDetails)
            if (result != null && result.isSuccessful) {
                updateTasksResponse.value = result.body()

            }

        }

    }


    fun getAssignedTasks(assignedTaskRequest: AssignedTaskRequest) {
        val retrofitService =
            RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)

        viewModelScope.launch {
            assignedTasks as MutableLiveData
            val result = retrofitService?.getAssignedtasks(assignedTaskRequest)
            if (result != null && result.isSuccessful) {
                assignedTasks.value = result.body()

            }

        }

    }



    fun findTasksBySearch(query:String) {
        val retrofitService =
            RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)

        viewModelScope.launch {
            pendingTasks as MutableLiveData
            val result = retrofitService?.getSeacrhableTasks(PendingItemsRequest(query))
            if (result != null && result.isSuccessful) {
                pendingTasks.value = result.body()

            }

        }

    }


}