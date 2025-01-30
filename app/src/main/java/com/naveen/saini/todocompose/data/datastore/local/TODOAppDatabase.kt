package com.naveen.saini.todocompose.data.datastore.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naveen.saini.todocompose.data.datastore.local.dao.TaskDao
import com.naveen.saini.todocompose.data.model.Task

@Database(version = 1, entities = [Task::class], exportSchema = false)
abstract class TODOAppDatabase:RoomDatabase() {
    abstract fun getTaskDao():TaskDao
}