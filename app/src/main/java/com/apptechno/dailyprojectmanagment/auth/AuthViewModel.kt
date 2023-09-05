package com.apptechno.dailyprojectmanagment.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptechno.dailyprojectmanagment.model.NetworkResponse
import com.apptechno.dailyprojectmanagment.model.User
import com.apptechno.dailyprojectmanagment.model.UserRequest
import com.apptechno.dailyprojectmanagment.network.GetDataService
import com.apptechno.dailyprojectmanagment.network.RetrofitClientInstance
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    val response : LiveData<NetworkResponse<User>> =MutableLiveData()


    fun onLoginClicked(userData: UserRequest){
        val retrofitService = RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)
        viewModelScope.launch {
            response as MutableLiveData
            val result = retrofitService?.loginUser(userData)
            if(result!=null && result.isSuccessful){
                response.value = result.body()

            }

        }

    }

    fun onRegisterClicked(userData: User){
        val retrofitService = RetrofitClientInstance.getRetrofitInstance()?.create(GetDataService::class.java)

        viewModelScope.launch {
            response as MutableLiveData
            val result = retrofitService?.registerUser(userData)
            if(result!=null && result.isSuccessful){
                response.value = result.body()

            }
        }

    }
}