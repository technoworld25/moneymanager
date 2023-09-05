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
    suspend fun getAllProjects(): Response<NetworkResponse<List<Project>>>

    @POST("saveTask.php")
    suspend fun addTask(@Body task: Task): Response<NetworkResponse<Task>>

    @POST("getTasks.php")
    suspend fun getTasks(@Body request: GetTaskRequest): Response<NetworkResponse<List<TaskResponse>>>

    @POST("updateProject.php")
    suspend fun updateProject(@Body projectDetails: Project): Response<NetworkResponse<Project>>

    @POST("updateTask.php")
    suspend fun updateTask(@Body task: Task): Response<NetworkResponse<Task>>

    @POST("assignedTasks.php")
    suspend fun getAssignedtasks(@Body request: AssignedTaskRequest): Response<NetworkResponse<List<TaskResponse>>>

    @POST("searchtask.php")
    suspend fun getSeacrhableTasks(@Body request: PendingItemsRequest): Response<NetworkResponse<List<TaskDetails>>>


}
