package com.apptechno.dailyprojectmanagment.ui.project

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.apptechno.dailyprojectmanagment.HomeActivity
import com.apptechno.dailyprojectmanagment.R.array.india_states
import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectBinding
import com.apptechno.dailyprojectmanagment.model.Project
import com.apptechno.dailyprojectmanagment.utility.ProjectUtility


class AddProjectFragment : Fragment() {

    private var _binding: FragmentProjectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val states = arrayOf("Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
    "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka",
    "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry")



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
      val galleryViewModel =
          ViewModelProvider(this).get(ProjectViewModel::class.java)

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

     setSpinners()

      _binding?.buttonSave?.setOnClickListener {

         saveProject()
      }
  }

  fun saveProject(){
      val projectName = _binding?.inputProjectname?.text.toString()
      val client = _binding?.inputClientName?.text.toString()
      val phone = _binding?.inputContactNumber?.text.toString()
      val address = _binding?.inputLocationName.toString()
      val poc = _binding?.inputPointOfContact?.text.toString()
      val pocNo = _binding?.inputClientName?.text.toString()
      val architect = _binding?.inputPointOfContact?.text.toString()
      val architectNo = _binding?.inputClientName?.text.toString()
      val selectedYear = _binding?.yearSpinner?.text.toString()
      val selectedState = _binding?.stateSpinner?.text.toString()
      val assigneeState = _binding?.responsibiltySpinner?.text.toString()

      if (projectName.isNullOrEmpty() && client.isNullOrEmpty() && address.isNullOrEmpty()
          && phone.isNullOrEmpty() && poc.isNullOrEmpty() && pocNo.isNullOrEmpty()
          && architect.isNullOrEmpty() && architectNo.isNullOrEmpty() && selectedYear.isNullOrEmpty() && assigneeState.isNullOrEmpty()){

          ProjectUtility.showToastMessage(requireContext(),"Please fill project details.")

      }else {

          var project = Project(
              projectName, client, address, phone, poc, pocNo, architect,
              architectNo, selectedYear, selectedYear, selectedState
          )
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