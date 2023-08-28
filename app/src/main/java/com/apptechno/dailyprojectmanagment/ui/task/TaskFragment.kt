package com.apptechno.dailyprojectmanagment.ui.task

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
import com.apptechno.dailyprojectmanagment.databinding.FragmentTaskListBinding
import com.apptechno.dailyprojectmanagment.model.Project
import com.apptechno.dailyprojectmanagment.model.TaskResponse
import com.apptechno.dailyprojectmanagment.ui.project.ProjectViewModel
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import kotlinx.coroutines.launch


class TaskFragment : Fragment(),com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener {
    lateinit var _binding: FragmentTaskListBinding
    private val binding get() = _binding!!
    lateinit var viewModel: ProjectViewModel
    lateinit var tasks : List<TaskResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val projectName = arguments?.getString("projectName").toString()
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Get Tasks"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getTasks(projectName)
        }

        viewModel.tasks.observe(this, Observer {
             tasks = it.data
            val adapter = MyTaskRecyclerViewAdapter(tasks,this)
            _binding.list.adapter = adapter
            ProjectUtility.showToastMessage(requireContext(),it.message)

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
        navController.navigate(R.id.action_taskFragment_to_addTaskFragment,bundle)

    }
}