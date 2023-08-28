package com.apptechno.dailyprojectmanagment.ui.task

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.apptechno.dailyprojectmanagment.R
import com.apptechno.dailyprojectmanagment.databinding.FragmentTaskBinding
import com.apptechno.dailyprojectmanagment.model.TaskResponse
import com.apptechno.dailyprojectmanagment.ui.project.onItemClickListener


class MyTaskRecyclerViewAdapter(
     var tasks: List<TaskResponse>,private val listener: onItemClickListener
) : RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder>() {


    init {
        val taskList = ArrayList(tasks)
        val task = TaskResponse("taskid","Assigner","Task Name","Description","State","Assignee",1,1,"Name","Client")
        taskList.add(0 ,task)
        tasks = taskList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.taskName.text = item.taskname
        holder.state.text = item.state
        holder.asignee.text = item.assignee

        // Set different colors for the 0th element
        if (position == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.grey_color))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))

        }
    }


    override fun getItemCount(): Int = tasks.size

    inner class ViewHolder(binding: FragmentTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        val taskName: TextView = binding.taskName
        val state: TextView = binding.state
        val asignee: TextView = binding.assignee

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