package com.apptechno.dailyprojectmanagment.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Daily Task Manager"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        setNavigations()
    }

    private fun setNavigations(){

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(com.apptechno.dailyprojectmanagment.R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        binding.btnAdProject.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_add_project)
            // navController.navigate(R.id.action)
        }

        binding.btnAdTask.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_addTaskFragment)
            // navController.navigate(R.id.action)
        }

        binding.btnGetProjects.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_projectListFragment)
        }

        binding.btnGetTasks.setOnClickListener {

            navController.navigate(R.id.action_nav_home_to_taskFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}