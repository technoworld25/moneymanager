package com.apptechno.dailyprojectmanagment.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apptechno.dailyprojectmanagment.databinding.FragmentTaskDetailsBinding
import com.apptechno.dailyprojectmanagment.model.TaskDetails


class TaskDetailsFragment : Fragment() {

    private lateinit var _binding:FragmentTaskDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        val root: View = _binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customObject = arguments?.getParcelable<TaskDetails>("taskResponse")
        val projectName =  "Project Name: "+customObject?.name
        val projectLead = "Project Lead: "+ customObject?.lead
        val poc = "Poc: "+ customObject?.poc
        val pocNo = "Poc No: "+ customObject?.pocNo
        val designation = "Designation: "+ customObject?.designation
        val address = "Address: "+ customObject?.address
        val state = "Year: "+ customObject?.projectState
        val architect = "Architect: "+ customObject?.architect
        val architectNo = "Architect No: "+ customObject?.architectNo
        val status ="Project status: "+ customObject?.status
        val year = "Year: "+ customObject?.year
        _binding.projectName.text=projectName
        _binding.projectLead.text= projectLead
        _binding.designation.text=designation
        _binding.address.text=address
        _binding.Poc.text=poc
        _binding.pocNo.text=pocNo
        _binding.architect.text=architect
        _binding.architectNo.text=architectNo
        _binding.state.text=state
        _binding.status.text=status
        _binding.year.text=year

         val taskName = "Task Name: "+ customObject?.taskname
         val taskDetails = "Task Details: "+ customObject?.description
         val taskState = "Task State: "+ customObject?.taskstate
         val taskAssignee = "Task Assignee: "+ customObject?.assignee
         val taskRequester = "Task Requester: "+ customObject?.assigner
        val date = "Date: "+ customObject?.date
        _binding.taskName.text= taskName
        _binding.taskDetails.text = taskDetails
        _binding.taskState.text=taskState
        _binding.taskAssignee.text= taskAssignee
        _binding.taskRequester.text= taskRequester


    }


}