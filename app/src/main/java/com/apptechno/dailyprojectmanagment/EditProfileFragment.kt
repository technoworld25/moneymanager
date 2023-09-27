package com.apptechno.dailyprojectmanagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apptechno.dailyprojectmanagment.auth.AuthViewModel
import com.apptechno.dailyprojectmanagment.databinding.FragmentEditProfileBinding
import com.apptechno.dailyprojectmanagment.model.User
import com.apptechno.dailyprojectmanagment.utility.Constants
import com.apptechno.dailyprojectmanagment.utility.SharedUtility
import kotlinx.coroutines.launch


class EditProfileFragment : Fragment() {

    private lateinit var binding  :FragmentEditProfileBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var utility: SharedUtility
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       utility = SharedUtility(requireContext())
       binding.inputUsername.setText(utility.getString(Constants.USERNAME,""))
       binding.inputEmail.setText(utility.getString(Constants.EMAIL,""))
       binding.inputPhone.setText(utility.getString(Constants.PHONE,""))
       binding.inputPassword.setText(utility.getString(Constants.PASSWORD,""))

        binding.btnSave.setOnClickListener {

            onSaveDetails()
        }
    }

    fun onSaveDetails(){

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        val username = binding.inputUsername.text.toString()
        val email = binding.inputEmail.text.toString()
        val phone = binding.inputPhone.text.toString()
        val password = binding.inputPassword.text.toString()
         val id = utility.getString(Constants.ID,"")

        lifecycleScope.launch {
            viewModel.onEditProfileClicked(User(id.toString(),username,email, phone, password))

        }

        viewModel.response.observe(this){

            if(it.status.equals("Success")){
                Toast.makeText(requireContext(),"User is updated successfully",Toast.LENGTH_SHORT).show()

                    utility.saveString(Constants.ID,id )
                    utility.saveString(Constants.USERNAME,username)
                    utility.saveString(Constants.EMAIL, email)
                    utility.saveString(Constants.PHONE, phone)
                    utility.saveString(Constants.PASSWORD,password)

            }

        }

    }


}