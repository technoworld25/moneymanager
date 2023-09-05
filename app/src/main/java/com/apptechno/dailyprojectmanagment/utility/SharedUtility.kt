package com.apptechno.dailyprojectmanagment.utility

import android.content.Context
import android.content.SharedPreferences

class SharedUtility(mContext: Context) {

    private var sharedPreferences:SharedPreferences?=null

    init {
        sharedPreferences= mContext.getSharedPreferences("login",Context.MODE_PRIVATE)
    }

    fun getString(key:String?,defaultValue:String?): String? {
        return sharedPreferences!!.getString(key,defaultValue)

    }

    fun saveString(key:String?,value:String?){
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getInteger(key:String?,defaultValue:Int): Int {
        return sharedPreferences!!.getInt(key,defaultValue)

    }

    fun saveInteger(key:String?,value:Int){
        val editor = sharedPreferences!!.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getBoolean(key:String?): Boolean {
        return sharedPreferences!!.getBoolean(key,false)

    }

    fun saveBoolean(key:String?,value:Boolean){
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

}