package com.apptechno.dailyprojectmanagment.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectItemBinding
import com.apptechno.dailyprojectmanagment.model.Project
import kotlinx.coroutines.launch


class ProjectListFragment : Fragment(), com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener{

    private var _binding: FragmentProjectItemBinding? = null
    private val binding get() = _binding!!
    lateinit var projectViewModel: ProjectViewModel
    lateinit var projects : List<Project>

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

        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Project List"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        projects = ArrayList()
        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
       _binding!!.list.layoutManager= LinearLayoutManager(context)
       _binding!!.list. addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        lifecycleScope.launch {
            projectViewModel.getProjects()
        }
        projectViewModel.projects.observe(this, Observer {

            val  adapter = MyProjectRecyclerViewAdapter(it.data,this)
            projects = it.data
            _binding!!.list.adapter = adapter
        })
      }

    override fun onItemClick(position: Int) {
        val item = projects[position - 1]
       val name = arguments?.getString("type").toString()
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(com.apptechno.dailyprojectmanagment.R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        if(name == "task") {

            val bundle = Bundle().apply {
                putString("projectId", item.projectId)
                putString("projectName", item.projectName)
                putString("requestedBy", item.asignee)
            }
            navController.navigate(R.id.action_projectListFragment_to_addTaskFragment, bundle)
        }else if(name == "editProject"){

            val bundle = Bundle().apply {
                putParcelable("projectResponse", item)
            }

            navController.navigate(R.id.action_projectListFragment_to_nav_add_project,bundle)

        }else if(name == "editTask"){
            val bundle = Bundle().apply {
                putString("projectId", item.projectId)
                putString("projectName", item.projectName)
                putString("requestedBy", item.asignee)

            }

            navController.navigate(R.id.action_projectListFragment_to_taskFragment,bundle)
        }
    }
}