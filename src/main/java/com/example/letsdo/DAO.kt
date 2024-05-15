package com.example.letsdo

import androidx.room.*

// Data Access Object (DAO) interface for interacting with the database
@Dao
interface DAO {
    // Insert a task into the database
    @Insert
    suspend fun insertTask(entity: Task)

    // Update a task in the database
    @Update
    suspend fun updateTask(entity: Task)

    // Delete a task from the database
    @Delete
    suspend fun deleteTask(entity: Task)

    // Delete all tasks from the database
    @Query("DELETE FROM to_do")
    suspend fun deleteAll()

    // Get all tasks from the database
    @Query("SELECT * FROM to_do")
    suspend fun getTasks(): List<CardInfo>

    // Get a task by its ID from the database
    @Query("SELECT * FROM to_do WHERE id = :id")
    suspend fun getTaskById(id: Int): CardInfo?

    // Get tasks by priority from the database
    @Query("SELECT * FROM to_do WHERE priority = :priority")
    suspend fun getTasksByPriority(priority: String): List<CardInfo>
}
