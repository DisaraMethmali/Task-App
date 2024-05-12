package com.example.taskmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagementapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: DatabaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        taskId = intent.getIntExtra("task_id", -1)

        // Ensure that taskId is valid (-1 indicates invalid)
        if (taskId == -1) {
            // Handle invalid taskId, such as showing an error message
            Toast.makeText(this, "Invalid Task ID", Toast.LENGTH_SHORT).show()
            finish() // Finish the activity if taskId is invalid
            return
        }

        val task = db.getTaskByID(taskId)

        binding.edittitle.setText(task.title)
        binding.editcontent.setText(task.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.edittitle.text.toString()
            val newContent = binding.editcontent.text.toString()

            val updatedTask = Task(taskId, newTitle, newContent)
            db.updateTask(updatedTask)

            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
            finish() // Finish the activity after saving changes
        }
    }
}
