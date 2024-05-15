package com.example.letsdo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// SplashScreen class to display a splash screen when the app starts
class SplashScreen : AppCompatActivity() {
    private lateinit var database: MyDatabase // Database instance

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "To_Do"
        ).build()

        // Fetch tasks from the database in a background coroutine
        GlobalScope.launch {
            DataObject.listdata = database.dao().getTasks() as MutableList<CardInfo>
        }

        // Start MainActivity after fetching data
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
