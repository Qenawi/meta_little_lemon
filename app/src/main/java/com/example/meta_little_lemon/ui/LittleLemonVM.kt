package com.example.meta_little_lemon.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.meta_little_lemon.ui.data.AppDatabase
import com.example.meta_little_lemon.ui.data.MenuItemRoom
import com.example.meta_little_lemon.ui.data.fetchMenu
import com.example.meta_little_lemon.ui.data.saveMenuToDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LittleLemonVM (app:Application):AndroidViewModel(app){

    private val database: AppDatabase

    init {
        database = Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    fun getAllDatabaseMenuItems(): LiveData<List<MenuItemRoom>> {
        return database.menuItemDao().getAll()
    }

    fun fetchMenuDataIfNeeded() {
        viewModelScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                saveMenuToDatabase(
                    database,
                    fetchMenu("https://raw.githubusercontent.com/Qenawi/meta_little_lemon/master/app/src/main/assets/rawJson.json")
                )
            }
        }
    }
}