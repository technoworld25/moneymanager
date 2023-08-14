package com.apptechno.dailyprojectmanagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment



class HomeActivity : AppCompatActivity() {


   lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()

    }


   fun init() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        setupNavigation()

    }

    fun setupNavigation(){

        navController.navigateUp()
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController,appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {
        navController.popBackStack()
        return true
    }




}