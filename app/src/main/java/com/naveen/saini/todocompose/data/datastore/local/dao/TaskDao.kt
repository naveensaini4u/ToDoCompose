package com.naveen.saini.todocompose.data.datastore.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.naveen.saini.todocompose.data.model.Task

@Dao
interface TaskDao {
    @Insert()
    suspend fun insert(task:Task)

    @Query("SELECT * FROM task")
    suspend fun getAllTasks():List<Task>

    //@Query("UPDATE")
}