package com.apptechno.dailyprojectmanagment.ui.project

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.apptechno.dailyprojectmanagment.placeholder.PlaceholderContent.PlaceholderItem
import com.apptechno.dailyprojectmanagment.databinding.FragmentProjectListBinding

class MyProjectRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyProjectRecyclerViewAdapter.ViewHolder>() {

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
        holder.name.text = item.id
        holder.state.text = item.content
        holder.assignee.text = item.id
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProjectListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.projectName
        val state: TextView = binding.state
        val assignee: TextView = binding.assignee

    }

}