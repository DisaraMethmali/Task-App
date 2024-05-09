package com.example.taskmanagementapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.Adapter.ToDoAdapter
import com.example.taskmanagementapp.Model.ToDoModel
import com.example.taskmanagementapp.Util.DataBaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Collections

class MainActivity2 : AppCompatActivity(), OnDialogCloseListner {
    private var mRecyclerview: RecyclerView? = null
    private var fab: FloatingActionButton? = null
    private var myDB: DataBaseHelper? = null
    private var mList: List<ToDoModel>? = null
    private var adapter: ToDoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mRecyclerview = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.fab)
        myDB = DataBaseHelper(this@MainActivity2)
        mList = ArrayList()
        adapter = ToDoAdapter(myDB!!, this@MainActivity2)

        mRecyclerview?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity2)
            adapter = this@MainActivity2.adapter
        }

        mList = myDB?.getAllTasks()
        mList?.let {
            Collections.reverse(it)
            adapter?.setTasks(it)
        }
        fab?.setOnClickListener {
            CreateTask.newInstance().show(supportFragmentManager, CreateTask.TAG)
        }


        val itemTouchHelper = ItemTouchHelper(RecyclerViewTouchHelper(adapter!!))
        itemTouchHelper.attachToRecyclerView(mRecyclerview)
    }

    override fun onDialogClose(dialogInterface: DialogInterface?) {
        mList = myDB?.getAllTasks()
        mList?.let {
            Collections.reverse(it)
            adapter?.setTasks(it)
            adapter?.notifyDataSetChanged()
        }
    }
}
