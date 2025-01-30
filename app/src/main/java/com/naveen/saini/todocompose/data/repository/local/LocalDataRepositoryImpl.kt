package com.naveen.saini.todocompose.data.repository.local

import com.naveen.saini.todocompose.data.datastore.local.dao.TaskDao
import com.naveen.saini.todocompose.data.model.Task
import javax.inject.Inject

class LocalDataRepositoryImpl @Inject constructor(val taskDao: TaskDao):LocalDataRepository {
    override suspend fun setTask(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun getTasks(): List<Task> = taskDao.getAllTasks()


    override suspend fun updateTask(id: Int): Task {
        TODO("Not yet implemented")
    }
}