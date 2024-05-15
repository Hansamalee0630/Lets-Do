package com.example.letsdo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.letsdo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Main activity displaying a list of tasks
class MainActivity : AppCompatActivity() {
    private lateinit var database: MyDatabase // Database instance
    private lateinit var binding: ActivityMainBinding // View binding instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Inflate the layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "To_Do"
        ).build()

        // Set click listener for add button
        binding.add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }

        // Set click listener for delete all button
        binding.deleteAll.setOnClickListener {
            // Clear all data from DataObject and database in a background coroutine
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            // Update RecyclerView
            setRecycler()
        }

        // Set up RecyclerView
        setRecycler()
    }

    // Set up RecyclerView with adapter and layout manager
    private fun setRecycler() {
        binding.recyclerView.adapter = Adapter(DataObject.getAllData())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
