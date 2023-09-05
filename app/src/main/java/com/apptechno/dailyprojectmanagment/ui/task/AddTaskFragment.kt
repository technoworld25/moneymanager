package com.apptechno.dailyprojectmanagment.ui.task

import android.R
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.databinding.FragmentAddTaskBinding
import com.apptechno.dailyprojectmanagment.model.Task
import com.apptechno.dailyprojectmanagment.model.TaskResponse
import com.apptechno.dailyprojectmanagment.ui.project.ProjectViewModel
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import kotlinx.coroutines.launch

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: TaskViewModel
    lateinit var id :String
    lateinit var project :String
    lateinit var type:String
    lateinit var taskId :String
    lateinit var mContext:Context


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Add New Task"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        setSpinners()
        showDetailsIfAvailable()

        _binding!!.buttonSave.setOnClickListener {

           saveTask()

        }
        viewModel.taskResponse.observe(this, Observer {

            ProjectUtility.showToastMessage(requireContext(),it.message)

        })
        viewModel.updateTasksResponse.observe(this, Observer {

            ProjectUtility.showToastMessage(requireContext(),it.message)

        })
    }

    fun showDetailsIfAvailable(){
        val customObject = arguments?.getParcelable<TaskResponse>("taskResponse")

        id = arguments?.getString("projectId").toString()
        project = arguments?.getString("projectName").toString()

        _binding!!.projectName.text = "Project Name : "+ project
        _binding!!.requestedBy.text = "Requested By :  "+ "Anupam22"

        if(customObject != null) {
             type = "edit"
            taskId = customObject.taskid
             id = customObject.id.toString()
            _binding!!.inputTaskName.setText(customObject!!.taskname)
            _binding!!.projectName.setText( "Project Name : "+ customObject!!.name)
            _binding!!.inputTaskDescription.setText(customObject!!.description)
            _binding!!.requestedBy.setText("Requested By :  "+customObject!!.assigner)
            _binding!!.assigneeSpinner.setText(customObject.assignee,false)
            _binding!!.stateSpinner.setText(customObject.state,false)
            (activity as HomeActivity)!!.supportActionBar!!.title = "Edit Task"

        }else{
            type ="add"
            (activity as HomeActivity)!!.supportActionBar!!.title = "Add New Task"
        }

    }
    lateinit var assigneesAdapter: ArrayAdapter<String>
    lateinit var statesAdapter: ArrayAdapter<String>

    fun setSpinners(){
        val assignees = arrayOf("Anupam L","Anupam22")
        assigneesAdapter = ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, assignees)

        _binding!!.assigneeSpinner.setAdapter(assigneesAdapter)

        val states = arrayOf("New","In Progress","Completed")
        statesAdapter = ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, states)

        _binding!!.stateSpinner.setAdapter(statesAdapter)
    }

    fun saveTask(){
        //val projectName = _binding!!.projectName.text.toString()
        val requestedBy = _binding!!.requestedBy.text.toString()
        val taskName = _binding!!.inputTaskName.text.toString()

        val taskDescription = _binding!!.inputTaskDescription.text.toString()
        val state = _binding!!.stateSpinner.text.toString()
        val asignee = _binding!!.assigneeSpinner.text.toString()


        lifecycleScope.launch {

            if(ProjectUtility.isConnectedToInternet(mContext)) {

                if(type.equals("add")) {
                    val task =Task("0",requestedBy,taskName,taskDescription,state,asignee,id.toInt())
                    viewModel.onSaveTaskClicked(task)
                }else{
                    val task =Task(taskId,requestedBy,taskName,taskDescription,state,asignee,id.toInt())
                    viewModel.updateTaskClicked(task)
                }
            }else{

                ProjectUtility.showToastMessage(mContext,"Internet is not available.")

            }



        }
    }


}