package com.example.taskmanagementapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.databinding.ActivityMainBinding

import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Collections

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Corrected binding class name
    private lateinit var db:DatabaseHelper
    private lateinit var taskAdapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) { // Added the override keyword and corrected the method signature
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Corrected binding class name
        setContentView(binding.root)
        db = DatabaseHelper(this)
        taskAdapter= TaskAdapter(db.getAllTasks(),this)

        binding.recyclerview.layoutManager=LinearLayoutManager(this)

        binding.recyclerview.adapter = taskAdapter
        binding.button2.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateTask::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        taskAdapter.refreshData(DatabaseHelper(this).getAllTasks())
    }
}