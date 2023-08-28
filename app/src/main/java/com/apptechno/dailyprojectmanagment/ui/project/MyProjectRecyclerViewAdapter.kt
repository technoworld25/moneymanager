package com.apptechno.dailyprojectmanagment.ui.project

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.apptechno.dailyprojectmanagment.R

import com.apptechno.dailyprojectmanagment.placeholder.PlaceholderContent.PlaceholderItem
import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectListBinding
import com.apptechno.dailyprojectmanagment.model.Project

interface onItemClickListener{
    fun onItemClick(position:Int)

}



class MyProjectRecyclerViewAdapter(
    private var values: List<Project>, private val listener: onItemClickListener
) : RecyclerView.Adapter<MyProjectRecyclerViewAdapter.ViewHolder>() {

    private val projectList = ArrayList(values)
    init {
        val project = Project("0","Project","Client","Address","Contact","Poc","PocNo",
            "Architect","ArchitectNo","Assignee","Year","State")
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

        // Set different colors for the 0th element
        if (position == 0) {
           holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.grey_color))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))

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