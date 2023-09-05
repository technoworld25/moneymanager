package com.apptechno.dailyprojectmanagment.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentAssignedTaskListBinding
import com.apptechno.dailyprojectmanagment.databinding.FragmentTaskListBinding
import com.apptechno.dailyprojectmanagment.model.AssignedTaskRequest
import com.apptechno.dailyprojectmanagment.model.TaskResponse
import com.apptechno.dailyprojectmanagment.model.UserRequest
import com.apptechno.dailyprojectmanagment.ui.task.MyTaskRecyclerViewAdapter
import com.apptechno.dailyprojectmanagment.ui.task.TaskViewModel
import com.apptechno.dailyprojectmanagment.utility.Constants
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import com.apptechno.dailyprojectmanagment.utility.SharedUtility
import kotlinx.coroutines.launch

class AssignedTaskFragment : Fragment(),com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener{

    lateinit var _binding : FragmentAssignedTaskListBinding
    lateinit var viewModel: TaskViewModel
    lateinit var mContext:Context
    lateinit var tasks:ArrayList<TaskResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentAssignedTaskListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
        tasks= ArrayList<TaskResponse>()
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Assigned Tasks"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        val sharedUtilty = SharedUtility(requireContext())
        val name = sharedUtilty.getString(Constants.USERNAME,"")
        lifecycleScope.launch {
            if(ProjectUtility.isConnectedToInternet(mContext)) {

                viewModel.getAssignedTasks(AssignedTaskRequest(name!!))
            }else{

                ProjectUtility.showToastMessage(mContext,"Internet is not available.")

            }
        }

        viewModel.assignedTasks.observe(this, Observer {

            val adapter = MyTaskRecyclerViewAdapter(it.data,this)
            tasks= it.data as ArrayList<TaskResponse>
            _binding.list.adapter = adapter

        })
    }


    override fun onItemClick(position: Int) {
        val item = tasks[position - 1]

        val bundle = Bundle().apply {
            putParcelable("taskResponse", item)
        }

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(com.apptechno.dailyprojectmanagment.R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_assignedTaskFragment_to_addTaskFragment,bundle)

    }


}