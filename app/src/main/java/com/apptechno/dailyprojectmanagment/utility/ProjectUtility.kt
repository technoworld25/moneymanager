package com.apptechno.dailyprojectmanagment.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

class ProjectUtility {

    companion object{

     fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
      }

     fun showToastMessage(context: Context,message:String){

         Toast.makeText(context,message,Toast.LENGTH_SHORT).show()

     }

    }
}