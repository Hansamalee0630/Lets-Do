package com.example.letsdo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.letsdo.databinding.ActivityCreateCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Activity for creating a new task
class CreateCard : AppCompatActivity() {
    private lateinit var database: MyDatabase // Database instance
    private lateinit var binding: ActivityCreateCardBinding // View binding instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "To_Do"
        ).build()

        // Set click listener for save button
        binding.saveButton.setOnClickListener {
            // Check if title and priority fields are not empty
            if (binding.createTitle.text.toString().trim { it <= ' ' }.isNotEmpty()
                && binding.createPriority.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                // Get title and priority from input fields
                val title = binding.createTitle.getText().toString()
                val priority = binding.createPriority.getText().toString()

                // Save data to DataObject
                DataObject.setData(title, priority)

                // Insert task into database in a background coroutine
                GlobalScope.launch {
                    database.dao().insertTask(Task(0, title, priority))
                }

                // Start MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
