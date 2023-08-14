package com.apptechno.dailyprojectmanagment.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apptechno.dailyprojectmanagment.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar!!.hide()
    }
}