package com.naveen.saini.todocompose.data.repository.local

import com.naveen.saini.todocompose.data.model.Task

interface LocalDataRepository {
    suspend fun setTask(task: Task)
    suspend fun getTasks():List<Task>

    suspend fun getTodayTasks():List<Task>
    suspend fun getFutureTasks():List<Task>
    suspend fun updateTask(task: Task)
}