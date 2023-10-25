package com.apptechno.dailyprojectmanagment.ui.task

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.databinding.FragmentAddTaskBinding
import com.apptechno.dailyprojectmanagment.model.Task
import com.apptechno.dailyprojectmanagment.model.TaskResponse
import com.apptechno.dailyprojectmanagment.utility.Constants
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import com.apptechno.dailyprojectmanagment.utility.SharedUtility
import kotlinx.coroutines.launch


class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel
    private lateinit var id :String
    private lateinit var project :String
    private lateinit var type:String
    private lateinit var taskId :String
    private lateinit var mContext:Context

    private val datePicker: DatePicker? = null
    private val showDateButton: Button? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        (activity as HomeActivity).supportActionBar?.elevation = 0f
        (activity as HomeActivity).supportActionBar?.title = "Add New Task"
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mContext = requireContext()
        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        setSpinners()
        showDetailsIfAvailable()
        binding.showDateButton.setOnClickListener {
            val year = datePicker?.year
            val month = datePicker?.month
            val day = datePicker?.dayOfMonth

            // Show the selected date in a dialog
            showDialog(year!!, month!!,day!!)
        }

        _binding!!.buttonSave.setOnClickListener {

           saveTask()

        }
        viewModel.taskResponse.observe(this) {

            ProjectUtility.showToastMessage(requireContext(), it.message)

        }
        viewModel.updateTasksResponse.observe(this) {

            ProjectUtility.showToastMessage(requireContext(), it.message)

        }
    }

    var username =""
    @SuppressLint("SetTextI18n")
    private fun showDetailsIfAvailable(){
        val customObject = arguments?.getParcelable<TaskResponse>("taskResponse")

        id = arguments?.getString("projectId").toString()
        project = arguments?.getString("projectName").toString()

        val sharedUtilty = SharedUtility(requireContext())
        username = sharedUtilty.getString(Constants.USERNAME,"").toString()

        _binding!!.projectName.text = "Project Name : $project"
        _binding!!.requestedBy.text = "Requested By :  "+ username

        if(customObject != null) {
             type = "edit"
            taskId = customObject.taskid
             id = customObject.id.toString()
            _binding!!.inputTaskName.setText(customObject.taskname)
            _binding!!.projectName.text = "Project Name : "+ customObject.name
            _binding!!.inputTaskDescription.setText(customObject.description)
            _binding!!.requestedBy.text = "Requested By :  "+customObject.assigner
            _binding!!.assigneeSpinner.setText(customObject.assignee,false)
            _binding!!.stateSpinner.setText(customObject.state,false)
            (activity as HomeActivity).supportActionBar!!.title = "Edit Task"

        }else{
            type ="add"
            (activity as HomeActivity).supportActionBar!!.title = "Add New Task"
        }

    }
    private lateinit var assigneesAdapter: ArrayAdapter<String>
    private lateinit var statesAdapter: ArrayAdapter<String>

    private val datePickerListener = DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
        // Do something with the selected date (e.g., display it in a TextView)
        val selectedDate = "Selected Date: $selectedYear-${selectedMonth + 1}-$selectedDay"
        // Replace this with your preferred way of displaying the selected date
        // (e.g., update a TextView, save it to a variable, etc.)
        // For this example, we'll just print it to the console.
        println(selectedDate)
    }

    private fun showDialog(year: Int, month: Int, day: Int) {
        showDialog(year, month, day)
    }


    private fun setSpinners(){
        var assignees = emptyArray<String>()
        lifecycleScope.launch {
            viewModel.getUsers()
        }
        viewModel.users.observe(this, Observer {
            val userlist = ArrayList<String>()
            it.data.forEach {
                userlist.add(it.username)
            }

            val userNameArray = userlist.map { it }.toTypedArray()
            assignees= userNameArray

            assigneesAdapter = ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, assignees)

            _binding!!.assigneeSpinner.setAdapter(assigneesAdapter)
        })

        val states = arrayOf("Ongoing","Completed")
        statesAdapter = ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, states)

        _binding!!.stateSpinner.setAdapter(statesAdapter)
    }

    private fun saveTask(){
        _binding!!.progressBar.visibility = View.VISIBLE

        //val projectName = _binding!!.projectName.text.toString()
        val requestedBy = _binding!!.requestedBy.text.toString()
        val taskName = _binding!!.inputTaskName.text.toString()

        val taskDescription = _binding!!.inputTaskDescription.text.toString()
        val state = _binding!!.stateSpinner.text.toString()
        val assignee = _binding!!.assigneeSpinner.text.toString()


        lifecycleScope.launch {

            if(ProjectUtility.isConnectedToInternet(mContext)) {

                if(type == "add") {
                    val task =Task(0,username,taskName,taskDescription,state,assignee,id.toInt())
                    viewModel.onSaveTaskClicked(task)
                }else{
                    val task =Task(taskId.toInt(),username,taskName,taskDescription,state,assignee,id.toInt())
                    viewModel.updateTaskClicked(task)
                }
            }else{
                _binding!!.progressBar.visibility = View.GONE
                ProjectUtility.showToastMessage(mContext,"Internet is not available.")

            }



        }

        viewModel.taskResponse.observe(this) {

            _binding!!.progressBar.visibility = View.GONE

        }

        viewModel.updateTasksResponse.observe(this) {

            _binding!!.progressBar.visibility = View.GONE

        }


    }


}