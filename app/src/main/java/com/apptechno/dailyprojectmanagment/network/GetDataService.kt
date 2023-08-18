package com.apptechno.dailyprojectmanagment.network

import com.apptechno.dailyprojectmanagment.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface GetDataService {

    @POST("login.php")
    suspend fun loginUser(@Body user: UserRequest): Response<NetworkResponse<User>>

    @POST("register.php")
    suspend fun registerUser(@Body user: User): Response<NetworkResponse<User>>

    @POST("saveProject.php")
    suspend fun registerProject(@Body projectDetails: Project): Response<NetworkResponse<Project>>

    @POST("getProjects.php")
    suspend fun getAllProjects(@Body projectDetails: Project): Response<NetworkResponse<List<Project>>>

    @POST("addTask.php")
    suspend fun addTask(@Body task: Task): Response<NetworkResponse<Task>>

    @POST("getTasks.php")
    suspend fun getTasks(): Response<NetworkResponse<List<Task>>>


}
