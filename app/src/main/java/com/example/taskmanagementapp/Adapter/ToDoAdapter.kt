package com.example.taskmanagementapp.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.CreateTask
import com.example.taskmanagementapp.MainActivity2
import com.example.taskmanagementapp.Model.ToDoModel
import com.example.taskmanagementapp.Util.DataBaseHelper

class ToDoAdapter(private val myDB: DataBaseHelper, private val activity: MainActivity2) :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    private var mList: List<ToDoModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(com.example.taskmanagementapp.R.layout.activity_task_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: ToDoModel = mList!![position]
        holder.mCheckBox.text = item.task
        holder.mCheckBox.isChecked = item.status != 0
        holder.mCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                myDB.updateStatus(item.id, 1)
            } else {
                myDB.updateStatus(item.id, 0)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    fun setTasks(mList: List<ToDoModel>?) {
        this.mList = mList
        notifyDataSetChanged()
    }

    fun deletTask(position: Int) {
        val item: ToDoModel = mList!![position]
        myDB.deleteTask(item.id)
        mList = mList!!.toMutableList().apply { removeAt(position) }
        notifyItemRemoved(position)
    }

    fun editItem(position: Int) {
        val item: ToDoModel = mList!![position]
        val bundle = Bundle().apply {
            putInt("id", item.id)
            putString("task", item.task)
        }
        val task = CreateTask().apply {
            var arguments = bundle
        }
        task.show(activity.supportFragmentManager, "CreateTask")
    }




    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mCheckBox: CheckBox = itemView.findViewById(com.example.taskmanagementapp.R.id.mcheckbox)
    }
}
