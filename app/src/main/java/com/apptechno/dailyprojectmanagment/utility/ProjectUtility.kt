package com.apptechno.dailyprojectmanagment.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast



class ProjectUtility {

    companion object{

     fun isConnectedToInternet(context: Context): Boolean {
         val connectivityManager =
            context. getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
         if (connectivityManager != null) {
             val activeNetworkInfo = connectivityManager.activeNetwork

             val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetworkInfo)
             return networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                         networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))


         }
         return false
      }

     fun showToastMessage(context: Context,message:String){

         Toast.makeText(context,message,Toast.LENGTH_SHORT).show()

     }
    }
}