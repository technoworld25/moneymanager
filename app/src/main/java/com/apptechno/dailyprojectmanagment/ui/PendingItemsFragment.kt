package com.apptechno.dailyprojectmanagment.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentPendingItemsBinding
import com.apptechno.dailyprojectmanagment.model.AssignedTaskRequest
import com.apptechno.dailyprojectmanagment.model.TaskDetails
import com.apptechno.dailyprojectmanagment.model.TaskResponse
import com.apptechno.dailyprojectmanagment.ui.task.MyTaskRecyclerViewAdapter
import com.apptechno.dailyprojectmanagment.ui.task.TaskViewModel
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import kotlinx.coroutines.launch


class PendingItemsFragment : Fragment(),com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener  {

    lateinit var _binding: FragmentPendingItemsBinding
    lateinit var viewModel: TaskViewModel
    lateinit var adapter:TaskDetailsAdapter
    lateinit var taskList:ArrayList<TaskDetails>
    lateinit var mContext:Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPendingItemsBinding.inflate(inflater, container, false)
        val root: View = _binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext=requireContext()
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Pending Items"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        _binding.searchView.isSubmitButtonEnabled = true
        _binding. searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                lifecycleScope.launch {
                    if(ProjectUtility.isConnectedToInternet(mContext)) {

                        viewModel.findTasksBySearch(query!!)
                    }else{

                        ProjectUtility.showToastMessage(mContext,"Internet is not available.")

                    }

                }
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }

        })


        viewModel.pendingTasks.observe(this, Observer {

             adapter = TaskDetailsAdapter(it.data, this)
             taskList = it.data as ArrayList<TaskDetails>
            _binding.list.adapter = adapter

            ProjectUtility.showToastMessage(requireContext(),it.message)

        })

    }



    override fun onItemClick(position: Int) {
       val item =  taskList.get(position)
        val bundle = Bundle().apply {
            putParcelable("taskResponse", item)
        }

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(com.apptechno.dailyprojectmanagment.R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_pendingItemsFragment_to_taskDetailsFragment,bundle)



    }


}

