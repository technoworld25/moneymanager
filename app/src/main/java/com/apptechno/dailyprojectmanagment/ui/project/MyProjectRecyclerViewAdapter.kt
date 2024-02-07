package com.apptechno.dailyprojectmanagment.ui.project

import android.R
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectListBinding
import com.apptechno.dailyprojectmanagment.model.Project


interface OnItemClickListener{
    fun onItemClick(position:Int)

}



class MyProjectRecyclerViewAdapter(
    private var values: List<Project>, private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyProjectRecyclerViewAdapter.ViewHolder>() {

    private val projectList = ArrayList(values)
    init {
        val project = Project("0","Project","Point Of Contact","Contact","Designation","Architect","ArchitectNo",
            "Lead","Year","Address","State","Status")
        projectList.add(0 ,project)
        values = projectList

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProjectListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.projectName
        holder.state.text = item.state
        holder.assignee.text = item.asignee

        val backgroundColor: Int
         if (holder.itemView.context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK === Configuration.UI_MODE_NIGHT_YES
        ) {
             if (position == 0) {
                 holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                    R.color.darker_gray))
             } else {
                 holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,com.apptechno.dailyprojectmanagment.R.color.black))

             }
        } else {
            // Dark mode is not enabled
             if (position == 0) {
                 holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,com.apptechno.dailyprojectmanagment.R.color.grey_color))
             } else {
                 holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))

             }
        }




    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProjectListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.taskName
        val state: TextView = binding.state
        val assignee: TextView = binding.assignee

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

    }

}