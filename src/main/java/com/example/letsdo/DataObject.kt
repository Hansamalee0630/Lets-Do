package com.example.letsdo

// Singleton object to manage data
object DataObject {
    // List to hold card information
    var listdata = mutableListOf<CardInfo>()

    // Method to add a new card
    fun setData(title: String, priority: String) {
        listdata.add(CardInfo(title, priority))
    }

    // Method to get all card data
    fun getAllData(): List<CardInfo> {
        return listdata
    }

    // Method to delete all card data
    fun deleteAll() {
        listdata.clear()
    }

    // Method to get card data at a specific position
    fun getData(pos: Int): CardInfo {
        return listdata[pos]
    }

    // Method to delete card data at a specific position
    fun deleteData(pos: Int) {
        listdata.removeAt(pos)
    }

    // Method to update card data at a specific position
    fun updateData(pos: Int, title: String, priority: String) {
        listdata[pos].title = title
        listdata[pos].priority = priority
    }

    // Method to add a list of card data
    fun addAllData(tasks: List<CardInfo>) {
        listdata.addAll(tasks)
    }

    // Method to update a specific task
    fun updateTask(task: CardInfo) {
        val index = listdata.indexOfFirst { it == task }
        if (index != -1) {
            listdata[index] = task
        }
    }
}
