package com.example.letsdo

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class representing a task in the database
@Entity(tableName = "To_Do")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int,         // Unique identifier for the task
    var title: String,   // Title of the task
    var priority: String // Priority of the task
)
