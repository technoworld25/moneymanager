package com.apptechno.dailyprojectmanagment.ui.task

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).supportActionBar!!.elevation = 0f
        (activity as HomeActivity)!!.supportActionBar!!.title = "Add New Task"
        (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun init(){
        val projectName = _binding!!.projectName.text.toString()
        val requestedBy = _binding!!.requestedBy.text.toString()
        val taskName = _binding!!.inputTaskName.text.toString()
        val taskDescription = _binding!!.inputTaskDescription.text.toString()

        val assignees = arrayOf("Anupam L","Anupam22")
        val assigneesAdapter: ArrayAdapter<String> = ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, assignees)
        assigneesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        _binding!!.assigneeSpinner.setAdapter(assigneesAdapter)

        _binding!!.buttonSave.setOnClickListener {



        }
    }


}