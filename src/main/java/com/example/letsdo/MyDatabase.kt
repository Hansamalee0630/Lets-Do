package com.example.letsdo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context

// Database class for managing tasks
@Database(entities = [Task::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    // Abstract method to get Data Access Object (DAO)
    abstract fun dao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        // Method to get a database instance
        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
