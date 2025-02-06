package com.naveen.saini.todocompose.data.datastore.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naveen.saini.todocompose.data.model.Task

@Dao
interface TaskDao {
    @Insert()
    suspend fun insert(task:Task)

    @Query("SELECT * FROM task")
    suspend fun getAllTasks():List<Task>

    @Query("SELECT * FROM task WHERE isToday = true")
    suspend fun getTodayTasks():List<Task>

    @Query("SELECT * FROM task WHERE isToday = false")
    suspend fun getFutureTasks():List<Task>

    @Query("SELECT * FROM task WHERE isCompleted = false AND isToday = false")
    suspend fun getUncompletedTasks():List<Task>
    @Update
    suspend fun updateTask(task: Task)
}