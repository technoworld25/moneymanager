package com.apptechno.dailyprojectmanagment.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.apptechno.dailyprojectmanagment.databinding.FragmentAssignedTaskBinding
import com.apptechno.dailyprojectmanagment.model.TaskDetails
import com.apptechno.dailyprojectmanagment.ui.project.OnItemClickListener


class TaskDetailsAdapter(
    private val values: List<TaskDetails>,private val listener: OnItemClickListener
) : RecyclerView.Adapter<TaskDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAssignedTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.taskName.text = item.taskname
        holder.state.text = item.taskstate
        holder.assignee.text = item.assignee

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAssignedTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val taskName: TextView = binding.taskName
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