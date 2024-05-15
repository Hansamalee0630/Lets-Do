package com.example.letsdo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.letsdo.databinding.ActivityUpdateCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Activity for updating a task
class UpdateCard : AppCompatActivity() {
    private lateinit var database: MyDatabase // Database instance
    private lateinit var binding: ActivityUpdateCardBinding // View binding instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view using view binding
        binding = ActivityUpdateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "To_Do"
        ).build()

        // Get task position from intent extra
        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            // Populate UI fields with task data
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)

            // Set click listener for delete button
            binding.deleteButton.setOnClickListener {
                // Delete task from DataObject and database
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Task(
                            pos + 1,
                            binding.createTitle.text.toString(),
                            binding.createPriority.text.toString()
                        )
                    )
                }
                // Navigate back to MainActivity
                myIntent()
            }

            // Set click listener for update button
            binding.updateButton.setOnClickListener {
                // Update task in DataObject and database
                DataObject.updateData(
                    pos,
                    binding.createTitle.text.toString(),
                    binding.createPriority.text.toString()
                )

                GlobalScope.launch {
                    database.dao().updateTask(
                        Task(
                            pos + 1, binding.createTitle.text.toString(),
                            binding.createPriority.text.toString()
                        )
                    )
                }
                // Navigate back to MainActivity
                myIntent()
            }
        }
    }

    // Method to navigate to MainActivity
    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
