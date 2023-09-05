package com.apptechno.dailyprojectmanagment.ui.project

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.DialogFragmentBinding
import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectItemBinding
import com.apptechno.dailyprojectmanagment.model.Project
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import kotlinx.coroutines.launch

class ProjectListFragment : Fragment(), com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener{

    private var _binding: FragmentProjectItemBinding? = null
    private val binding get() = _binding!!
    lateinit var projectViewModel: ProjectViewModel
    lateinit var projects : List<Project>
    lateinit var filteredProjects : List<Project>
    lateinit var adapter: MyProjectRecyclerViewAdapter
    lateinit var mContext:Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProjectItemBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext= requireContext()
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Project List"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        projects = ArrayList()
        filteredProjects = ArrayList()
        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
       _binding!!.list.layoutManager= LinearLayoutManager(context)
       _binding!!.list. addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        lifecycleScope.launch {

            if(ProjectUtility.isConnectedToInternet(mContext)) {
                projectViewModel.getProjects()
            }else{

                ProjectUtility.showToastMessage(mContext,"Internet is not available.")

            }

        }
        projectViewModel.projects.observe(this, Observer {
             projects = it.data
             filteredProjects= projects
             adapter = MyProjectRecyclerViewAdapter(projects,this)
            _binding!!.list.adapter = adapter
        })

        _binding!!.projectStateSpinner.setOnItemClickListener { parent, view, position, id ->

             val selectedItem = parent.getItemAtPosition(position) as String
             filteredProjects = projects.filter { it.state == selectedItem}
             adapter = MyProjectRecyclerViewAdapter(filteredProjects,this)
            _binding!!.list.adapter = adapter

        }
      }

    override fun onResume() {
        super.onResume()

        val states = arrayOf("Ongoing","Completed")
        val statesAdapter: ArrayAdapter<String> = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, states)
        _binding!!.projectStateSpinner.setAdapter(statesAdapter)
        statesAdapter.filter.filter("")
    }

    override fun onItemClick(position: Int) {
        val item = filteredProjects[position-1]
        val name = arguments?.getString("type").toString()
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(com.apptechno.dailyprojectmanagment.R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        val dialogMainBinding: DialogFragmentBinding = DialogFragmentBinding.inflate(LayoutInflater.from(requireContext()))

        // Initialize dialog
        val dialog = Dialog(requireContext());
        dialog.setContentView(dialogMainBinding.getRoot());
        dialog.show()
        dialogMainBinding.editProjects.setOnClickListener {
            dialog.dismiss()
            val bundle = Bundle().apply {
                putParcelable("projectResponse", item)
            }

            navController.navigate(R.id.action_projectListFragment_to_nav_add_project,bundle)
        }
        dialogMainBinding.showTasks.setOnClickListener {

            dialog.dismiss()
            val bundle = Bundle().apply {
                putString("projectId", item.projectId)
                putString("projectName", item.projectName)
                putString("requestedBy", item.asignee)

            }
            navController.navigate(R.id.action_projectListFragment_to_taskFragment,bundle)
        }

//        if(name == "task") {
//
//            val bundle = Bundle().apply {
//                putString("projectId", item.projectId)
//                putString("projectName", item.projectName)
//                putString("requestedBy", item.asignee)
//            }
//
//            navController.navigate(R.id.action_projectListFragment_to_addTaskFragment, bundle)
//        }else if(name == "editProject"){
//
//
//
//        }else if(name == "editTask"){
//            val bundle = Bundle().apply {
//                putString("projectId", item.projectId)
//                putString("projectName", item.projectName)
//                putString("requestedBy", item.asignee)
//
//            }
//            navController.navigate(R.id.action_projectListFragment_to_taskFragment,bundle)
//        }
    }
}