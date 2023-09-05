package com.apptechno.dailyprojectmanagment.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentHomeBinding
import com.apptechno.dailyprojectmanagment.utility.Constants
import com.apptechno.dailyprojectmanagment.utility.SharedUtility


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity).supportActionBar!!.title = "Daily Task Manager"
        (activity as HomeActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val textView: TextView = binding.textHome

        val sharedUtility = SharedUtility(requireContext())
        textView.text = sharedUtility.getString(Constants.USERNAME,"")

        setNavigations()
    }

    private fun setNavigations(){

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        binding.btnAdProject.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_add_project)

        }

        binding.btnAdTask.setOnClickListener {
            val bundle = Bundle().apply {
                putString("type","task" )

            }
            navController.navigate(R.id.action_nav_home_to_projectListFragment,bundle)

        }

        binding.btnGetProjects.setOnClickListener {
            val bundle = Bundle().apply {
                putString("type","editProject" )

            }
            navController.navigate(R.id.action_nav_home_to_projectListFragment,bundle)
        }

        binding.btnGetTasks.setOnClickListener {
            val bundle = Bundle().apply {
                putString("type","editTask" )

            }

            navController.navigate(R.id.action_nav_home_to_projectListFragment,bundle)
        }

        binding.btnAssignedTasks.setOnClickListener {

            navController.navigate(R.id.action_nav_home_to_assignedTaskFragment)
        }

        binding.btnpendingItems.setOnClickListener {


            navController.navigate(R.id.action_nav_home_to_pendingItemsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}