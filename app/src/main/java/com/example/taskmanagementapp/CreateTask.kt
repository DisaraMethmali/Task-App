package com.example.taskmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagementapp.databinding.ActivityCreateTaskBinding

class CreateTask : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DatabaseHelper(this)

        binding.buttonSave.setOnClickListener {
            val title = binding.edittext.text.toString()
            val content = binding.edittext2.text.toString()
            val task = Task(id = 0, title = title, content = content) // Assuming id should be auto-generated
            db.insertTask(task)
            finish()
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
