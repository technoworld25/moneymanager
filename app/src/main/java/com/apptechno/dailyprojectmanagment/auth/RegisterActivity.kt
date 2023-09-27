package com.apptechno.dailyprojectmanagment.auth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apptechno.dailyprojectmanagment.databinding.ActivityRegisterBinding
import com.apptechno.dailyprojectmanagment.model.User
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var mContext:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext = this@RegisterActivity
        supportActionBar!!.hide()
    }

    fun onRegisterButtonClicked(view :View){

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        val email = binding.inputEmail.text.toString()
        val phone = binding.inputPhone.text.toString()
        val password = binding.inputPassword.text.toString()
        val username = binding.inputUsername.text.toString()

        if (username.isNotEmpty() &&
            email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()
        ) {

            lifecycleScope.launch(Dispatchers.IO) {

                if(ProjectUtility.isConnectedToInternet(mContext)) {
                viewModel.onRegisterClicked(User("0",username, email, phone, password)) }else{

                    ProjectUtility.showToastMessage(mContext,"Internet is not available.")
                }
            }

            viewModel.response.observe(this) {

                if (it?.data != null) {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    finish()

                }
            }

        }else{
            ProjectUtility.showToastMessage(mContext,"Please add your details")

        }
    }

}