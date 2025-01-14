package com.apptechno.dailyprojectmanagment.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.databinding.ActivityLoginBinding
import com.apptechno.dailyprojectmanagment.model.UserRequest
import com.apptechno.dailyprojectmanagment.utility.Constants
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import com.apptechno.dailyprojectmanagment.utility.SharedUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var mContext: Context
    private lateinit var sharedUtility: SharedUtility

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext= this@LoginActivity
        sharedUtility= SharedUtility(mContext)
       val login= sharedUtility.getBoolean(Constants.LOGGED_IN)
        if (login){
            startActivity(Intent(mContext, HomeActivity::class.java))
            finish()
        }

        supportActionBar!!.hide()
    }

    fun onLoginClicked(view: View) {


        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        val username = binding.inputUsername.text.toString()
        val password = binding.inputPassword.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {

            // launching a new coroutine
            lifecycleScope.launch(Dispatchers.IO) {
                if(ProjectUtility.isConnectedToInternet(mContext)) {
                    try {
                        viewModel.onLoginClicked(UserRequest(username, password))
                    }catch (e:Exception){
                        Log.d("Exception",e.message!!)
                    }

                }else{

                    ProjectUtility.showToastMessage(mContext,"Internet is not available.")

                }
            }

            viewModel.response.observe(this) {

                if (it != null) {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    if (username == it.data.username) {

                        sharedUtility.saveBoolean(Constants.LOGGED_IN, true)
                        sharedUtility.saveString(Constants.EMAIL, it.data.email)
                        sharedUtility.saveString(Constants.EMAIL, it.data.email)
                        sharedUtility.saveString(Constants.USERNAME, it.data.username)
                        sharedUtility.saveString(Constants.PHONE, it.data.phone)
                        sharedUtility.saveString(Constants.PASSWORD, it.data.password)
                        startActivity(Intent(mContext, HomeActivity::class.java))
                    }
                }
            }

        }else{
            ProjectUtility.showToastMessage(mContext,"Please add your username and password")

        }
    }


    fun onCreateNewAccountClicked(view: View){

        startActivity( Intent(this, RegisterActivity::class.java))

    }




}