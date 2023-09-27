package com.apptechno.dailyprojectmanagment.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {
  companion object {
      private var retrofit: Retrofit? = null
     //private val BASE_URL = "http://192.168.1.117:81/dailydatatracker/"
      private val BASE_URL="https://vlengineers.com/dailytracker/"
     // private val BASE_URL="http://192.168.0.227:81/dailydatatracker/"
      val gson =  GsonBuilder()
      .setLenient()
      .create()

      fun getRetrofitInstance(): Retrofit? {
          if (retrofit == null) {
              retrofit = Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create(gson))
                  .build()
          }
          return retrofit
      }
  }
}