package com.apptechno.dailyprojectmanagment.network

import com.apptechno.dailyprojectmanagment.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST



interface GetDataService {

    @POST("login.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun loginUser(@Body user: UserRequest): Response<NetworkResponse<User>>

    @POST("register.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun registerUser(@Body user: User): Response<NetworkResponse<User>>

    @POST("saveProject.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun registerProject(@Body projectDetails: Project): Response<NetworkResponse<Project>>

    @POST("getProjects.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun getAllProjects(): Response<NetworkResponse<List<Project>>>

    @POST("savetask.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun addTask(@Body task: Task): Response<NetworkResponse<Task>>

    @POST("getTasks.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun getTasks(@Body request: GetTaskRequest): Response<NetworkResponse<List<TaskResponse>>>

    @POST("updateProject.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun updateProject(@Body projectDetails: Project): Response<NetworkResponse<Project>>

    @POST("updatetask.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun updateTask(@Body task: Task): Response<NetworkResponse<Task>>

    @POST("assignedTasks.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun getAssignedtasks(@Body request: AssignedTaskRequest): Response<NetworkResponse<List<TaskResponse>>>

    @POST("searchtask.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun getSeacrhableTasks(@Body request: PendingItemsRequest): Response<NetworkResponse<List<TaskDetails>>>

    @POST("users.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun getUsers(): Response<NetworkResponse<List<User>>>

    @POST("updateprofile.php")
    @Headers("X-Auth-Key:daily2023")
    suspend fun editProfile(): Response<NetworkResponse<User>>



}
