package com.example.taskmanagementapp
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CreateTask : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
    }

    fun showFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag("MyFragmentTag")
        if (fragment != null) {
            fragmentTransaction.show(fragment)
            fragmentTransaction.commit()
        }
    }



    fun show(supportFragmentManager: FragmentManager, fragment: String, tag: String) {
        // Begin a transaction to add the fragment
        val transaction = supportFragmentManager.beginTransaction()

        // Check if the fragment is already added to avoid adding it multiple times
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            // Add the fragment using a tag for later retrieval if needed
            transaction.add(android.R.id.content, fragment, tag)
        }

        // Show the fragment
        transaction.show(fragment)

        // Commit the transaction
        transaction.commit()
    }

    companion object {
        const val TAG = "CreateTaskDialog"
        fun newInstance(): CreateTask {

            return CreateTask()
        }
    }


}