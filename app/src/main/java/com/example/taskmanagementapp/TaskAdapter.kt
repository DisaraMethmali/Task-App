package com.example.taskmanagementapp

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TaskAdapter (private var tasks:List<Task>,context: Context):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.edittext)
        val contentTextView: TextView = itemView.findViewById(R.id.edittext2)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentNote = tasks[position]
        holder.titleTextView.text = currentNote.title
        holder.contentTextView.text = currentNote.content
    }
    fun refreshData(newTasks: List<Task>) {
        tasks=newTasks
        notifyDataSetChanged() // Notify the adapter of the data change
    }
}