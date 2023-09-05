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
        val client = "Client: "+ customObject?.client
        val phone = "Phone: "+ customObject?.phone
        val poc = "Poc: "+ customObject?.poc
        val pocNo = "Poc No: "+ customObject?.pocNo
        val architect = "Architect: "+ customObject?.architect
        val architectNo = "Architect: "+ customObject?.architect
        val state ="Project State: "+ customObject?.projectState
        val year = "Year: "+ customObject?.year
        _binding.projectName.text=projectName
        _binding.projectLead.text= projectLead
        _binding.client.text=client
        _binding.phone.text=phone
        _binding.Poc.text=poc
        _binding.pocNo.text=pocNo
        _binding.architect.text=architect
        _binding.architectNo.text=architectNo
        _binding.state.text=state
        _binding.year.text=year

         val taskName = "Task Name: "+ customObject?.taskname
         val taskDetails = "Task Details: "+ customObject?.description
         val taskState = "Task State: "+ customObject?.taskstate
         val taskAssignee = "Task Assignee: "+ customObject?.assignee
         val taskRequester = "Task Requester: "+ customObject?.assigner
        _binding.taskName.text= taskName
        _binding.taskDetails.text = taskDetails
        _binding.taskState.text=taskState
        _binding.taskAssignee.text= taskAssignee
        _binding.taskRequester.text= taskRequester


    }


}