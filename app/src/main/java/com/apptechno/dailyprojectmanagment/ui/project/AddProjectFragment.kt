package com.apptechno.dailyprojectmanagment.ui.project

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R.array.india_states
import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectBinding
import com.apptechno.dailyprojectmanagment.model.Project
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility
import kotlinx.coroutines.launch


class AddProjectFragment : Fragment() {

    private var _binding: FragmentProjectBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ProjectViewModel
    lateinit var type:String
    lateinit var projectId:String

    val states = arrayOf("New","In Progress","Completed" )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentProjectBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


  fun init(){
      (activity as HomeActivity).supportActionBar!!.elevation = 0f
      (activity as HomeActivity)!!.supportActionBar!!.title = "Add New Project"
      (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
      viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
      setSpinners()
      showDetailsIfAvailable()

      _binding?.buttonSave?.setOnClickListener {

         saveProject()
      }

      viewModel.response.observe(this, Observer {

          Log.d("SS",it.toString())
          if(it!=null){

              ProjectUtility.showToastMessage(requireContext(),it.message)

          }
      })

      viewModel.updateProjectResponse.observe(this, Observer {

          Log.d("SS",it.toString())
          if(it!=null){

              ProjectUtility.showToastMessage(requireContext(),it.message)

          }
      })
  }

    fun showDetailsIfAvailable(){

        val customObject = arguments?.getParcelable<Project>("projectResponse")
        if(customObject != null){
             type = "edit"
             projectId = customObject.projectId
            _binding?.inputProjectname?.setText(customObject.projectName)
            _binding?.inputClientName?.setText(customObject.clientName)
            _binding?.inputContactNumber?.setText(customObject.contactNo)
            _binding?.inputLocationName?.setText(customObject.address)
            _binding?.inputPoc?.setText(customObject.poc)
            _binding?.inputPocNo?.setText(customObject.pocNo)
            _binding?.inputArchitectName?.setText(customObject.architect)
            _binding?.inputArchitectContactNumber?.setText(customObject.architectNo)
            _binding!!.responsibiltySpinner?.setText(customObject.asignee,false)
            _binding!!.yearSpinner?.setText(customObject.year,false)
            _binding!!.stateSpinner?.setText(customObject.state,false)
            (activity as HomeActivity)!!.supportActionBar!!.title = "Edit Project"

        }else{
            type = "add"
            (activity as HomeActivity)!!.supportActionBar!!.title = "Add New Project"

        }
    }

  fun saveProject(){
      val projectName = _binding?.inputProjectname?.text.toString()
      val client = _binding?.inputClientName?.text.toString()
      val phone = _binding?.inputContactNumber?.text.toString()
      val address = _binding?.inputLocationName?.text.toString()
      val poc = _binding?.inputPoc?.text.toString()
      val pocNo = _binding?.inputPocNo?.text.toString()
      val architect = _binding?.inputArchitectName?.text.toString()
      val architectNo = _binding?.inputArchitectContactNumber?.text.toString()
      val selectedYear = _binding?.yearSpinner?.text.toString()
      val selectedState = _binding?.stateSpinner?.text.toString()
      val assigneeState = _binding?.responsibiltySpinner?.text.toString()

      if (projectName.isNullOrEmpty() || client.isNullOrEmpty() || address.isNullOrEmpty()
          && phone.isNullOrEmpty() || poc.isNullOrEmpty() || pocNo.isNullOrEmpty()
          && architect.isNullOrEmpty() || architectNo.isNullOrEmpty() || selectedYear.isNullOrEmpty() || assigneeState.isNullOrEmpty()){

          ProjectUtility.showToastMessage(requireContext(),"Please fill project details.")

      }else {
          lifecycleScope.launch {
              if(type.equals("add")){
                  var project = Project("0",
                      projectName, client, address, phone, poc, pocNo, architect,
                      architectNo, assigneeState, selectedYear, selectedState
                  )
                viewModel.onSaveProjectClicked(project)
               }
              else{
                  var project = Project(projectId,
                      projectName, client, address, phone, poc, pocNo, architect,
                      architectNo, assigneeState, selectedYear, selectedState
                  )
                  viewModel.updateProjectClicked(project)
              }
          }
      }

  }

    fun setSpinners(){
        getResources().getStringArray(india_states);
        val stateAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context!!,
            R.layout.simple_spinner_item,
            states
        )
        _binding!!.stateSpinner.setAdapter(stateAdapter)

        val years = arrayOf("2020","2021","2022","2023","2024","2025")
        val yearAdapter: ArrayAdapter<String> = ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        _binding!!.yearSpinner.setAdapter(yearAdapter)

        val assignees = arrayOf("Anupam L","Anupam22")
        val assigneesAdapter: ArrayAdapter<String> = ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, assignees)
        assigneesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        _binding!!.responsibiltySpinner.setAdapter(assigneesAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}