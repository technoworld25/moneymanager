package com.apptechno.dailyprojectmanagment.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       supportActionBar!!.hide()
    }

    fun onLoginClicked(view: View){

       startActivity( Intent(this, HomeActivity::class.java))

    }

    fun onCreateNewAccountClicked(view: View){

        startActivity( Intent(this, RegisterActivity::class.java))

    }



}