package com.apptechno.dailyprojectmanagment.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptechno.dailyprojectmanagment.model.*
import com.apptechno.dailyprojectmanagment.network.GetDataService
import com.apptechno.dailyprojectmanagment.network.RetrofitClientInstance
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {



    val response : LiveData<NetworkResponse<Project>> =MutableLiveData()
    val projects : LiveData<NetworkResponse<List<Project>>> =MutableLiveData()
    val updateProjectResponse : LiveData<NetworkResponse<Project>> = MutableLiveData()


    fun onSaveProjectClicked(projectDetails: Project){
        val retrofitService = RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)
        viewModelScope.launch {
            response as MutableLiveData
            val result = retrofitService?.registerProject(projectDetails)
            if(result!=null && result.isSuccessful){
                response.value = result.body()

            }

        }

    }

    fun getProjects(){
        val retrofitService = RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)
        projects as MutableLiveData
        viewModelScope.launch {

            val result = retrofitService?.getAllProjects()
            if(result!=null && result.isSuccessful){

               projects.value = result.body()
            }else{


               // ProjectUtility.showToastMessage()
            }
        }

    }


    fun updateProjectClicked(projectDetails: Project) {
        val retrofitService =
            RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)

        viewModelScope.launch {
            updateProjectResponse as MutableLiveData
            val result = retrofitService?.updateProject(projectDetails)
            if (result != null && result.isSuccessful) {
                updateProjectResponse.value = result.body()

            }

        }

    }



}

