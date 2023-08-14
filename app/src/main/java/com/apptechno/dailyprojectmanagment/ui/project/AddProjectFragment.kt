package com.apptechno.dailyprojectmanagment.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.apptechno.dailyprojectmanagment.HomeActivity

import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectBinding

class AddProjectFragment : Fragment() {

    private var _binding: FragmentProjectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
      (activity as HomeActivity)!!.supportActionBar!!.title = "Add new project"
      (activity as HomeActivity)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
      val galleryViewModel =
          ViewModelProvider(this).get(ProjectViewModel::class.java)

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

  }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }






}