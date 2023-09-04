package com.apptechno.dailyprojectmanagment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.databinding.FragmentAssignedTaskListBinding
import com.apptechno.dailyprojectmanagment.databinding.FragmentTaskListBinding
import com.apptechno.dailyprojectmanagment.model.AssignedTaskRequest
import com.apptechno.dailyprojectmanagment.ui.task.MyTaskRecyclerViewAdapter
import com.apptechno.dailyprojectmanagment.ui.task.TaskViewModel
import com.apptechno.dailyprojectmanagment.utility.Constants
import com.apptechno.dailyprojectmanagment.utility.SharedUtility
import kotlinx.coroutines.launch

class AssignedTaskFragment : Fragment(),com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener{

    lateinit var _binding : FragmentAssignedTaskListBinding
    lateinit var viewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentAssignedTaskListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Assigned Tasks"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        val sharedUtilty = SharedUtility(requireContext())
        val name = sharedUtilty.getString(Constants.USERNAME,"")
        lifecycleScope.launch {
            viewModel.getAssignedTasks(AssignedTaskRequest(name!!))
        }

        viewModel.assignedTasks.observe(this, Observer {

            val adapter = MyTaskRecyclerViewAdapter(it.data,this)
            _binding.list.adapter = adapter

        })
    }


    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }


}