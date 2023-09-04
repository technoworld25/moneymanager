package com.apptechno.dailyprojectmanagment.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentAddTaskBinding
import com.apptechno.dailyprojectmanagment.databinding.FragmentTaskDetailsBinding
import com.apptechno.dailyprojectmanagment.model.TaskDetails
import com.apptechno.dailyprojectmanagment.model.TaskResponse

class TaskDetailsFragment : Fragment() {

    lateinit var _binding:FragmentTaskDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        val root: View = _binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customObject = arguments?.getParcelable<TaskDetails>("taskResponse")
        _binding.projectName.setText( "Project Name: "+customObject!!.name)
        _binding.projectLead.setText("Project Lead: "+ customObject!!.lead)
        _binding.client.setText("Client: "+ customObject!!.client)
        _binding.phone.setText("Phone: "+ customObject!!.phone)
        _binding.Poc.setText("Poc: "+ customObject!!.poc)
        _binding.pocNo.setText("Poc No: "+ customObject!!.pocNo)
        _binding.architect.setText("Architect: "+ customObject!!.architect)
        _binding.architectNo.setText("Architect No: "+ customObject!!.architectNo)
        _binding.state.setText("Project State: "+ customObject!!.projectState)
        _binding.year.setText("Year: "+ customObject!!.year)


        _binding.taskName.setText("Task Name: "+ customObject!!.taskname)
        _binding.taskDetails.setText("Task Details: "+ customObject!!.description)
        _binding.taskState.setText("Task State: "+ customObject!!.taskstate)
        _binding.taskAssignee.setText("Task Assignee: "+ customObject!!.assignee)
        _binding.taskRequester.setText("Task Requester: "+ customObject!!.assigner)


    }


}