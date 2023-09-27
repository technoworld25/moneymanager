package com.apptechno.dailyprojectmanagment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.apptechno.dailyprojectmanagment.auth.LoginActivity
import com.apptechno.dailyprojectmanagment.databinding.DrawerLayoutBinding
import com.apptechno.dailyprojectmanagment.utility.Constants
import com.apptechno.dailyprojectmanagment.utility.SharedUtility
import com.google.android.material.navigation.NavigationView


class HomeActivity : AppCompatActivity() {

    lateinit var binding: DrawerLayoutBinding
    lateinit var navController:NavController
    lateinit var appBarConfiguration :AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DrawerLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.destination1, R.id.destination1), drawerLayout
        )
        val navView: NavigationView = binding.navigationView
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId();

        if (id == R.id.editProfile) {

            navController.navigate(R.id.action_nav_home_to_editProfileFragment)
            return true;
        }

        if (id == R.id.logOut) {
           val sharedUtility = SharedUtility(this)
            sharedUtility.saveBoolean(Constants.LOGGED_IN,false)
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
            return true;
        }

        return super.onOptionsItemSelected(item)


    }


   fun init() {


    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

      //  navController.popBackStack()
       // return true
    }




}