package com.apptechno.dailyprojectmanagment.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentTaskListBinding
import com.apptechno.dailyprojectmanagment.model.Project
import com.apptechno.dailyprojectmanagment.model.TaskResponse
import com.apptechno.dailyprojectmanagment.ui.project.MyProjectRecyclerViewAdapter
import com.apptechno.dailyprojectmanagment.ui.project.ProjectViewModel
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import kotlinx.coroutines.launch


class TaskFragment : Fragment(),com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener {
    lateinit var _binding: FragmentTaskListBinding
    private val binding get() = _binding!!
    lateinit var viewModel: TaskViewModel
    lateinit var tasks : List<TaskResponse>
    lateinit var filteredTasks : List<TaskResponse>
    lateinit var adapter: MyTaskRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val states = arrayOf("Ongoing","Completed")
        val statesAdapter: ArrayAdapter<String> = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, states)
        _binding!!.taskStateSpinner.setAdapter(statesAdapter)
        statesAdapter.filter.filter("")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val projectName = arguments?.getString("projectName").toString()
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Get Tasks"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getTasks(projectName)
        }

        viewModel.tasks.observe(this, Observer {
             tasks = it.data
            filteredTasks= tasks
            val adapter = MyTaskRecyclerViewAdapter(tasks,this)
            _binding.list.adapter = adapter
            ProjectUtility.showToastMessage(requireContext(),it.message)

        })

        _binding!!.taskStateSpinner.setOnItemClickListener { parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position) as String
            filteredTasks = tasks.filter { it.state == selectedItem}
            adapter = MyTaskRecyclerViewAdapter(filteredTasks,this)

            _binding!!.list.adapter = adapter

        }

    }

    override fun onItemClick(position: Int) {
        val item = filteredTasks[position - 1]

        val bundle = Bundle().apply {
            putParcelable("taskResponse", item)
        }

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(com.apptechno.dailyprojectmanagment.R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_taskFragment_to_addTaskFragment,bundle)

    }
}