package com.example.letsdo

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.*
import android.widget.LinearLayout
import android.widget.TextView

// Adapter class for RecyclerView
class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // ViewHolder class to hold views for each item in RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.title) // Title TextView
        var priority = itemView.findViewById<TextView>(R.id.priority) // Priority TextView
        var layout = itemView.findViewById<LinearLayout>(R.id.myLayout) // Layout to set background color
    }

    // Creates ViewHolder instances
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate layout for each item
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    // Binds data to views in each item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set background color based on priority
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }

        // Set title and priority text
        holder.title.text = data[position].title
        holder.priority.text = data[position].priority

        // Set click listener to open UpdateCard activity with intent passing item position
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }
    }

    // Returns number of items in data list
    override fun getItemCount(): Int {
        return data.size
    }
}
