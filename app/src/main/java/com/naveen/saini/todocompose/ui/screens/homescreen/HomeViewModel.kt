package com.naveen.saini.todocompose.ui.screens.homescreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveen.saini.todocompose.data.model.Task
import com.naveen.saini.todocompose.data.repository.local.LocalDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: LocalDataRepository):ViewModel() {
    private val _tasksList = MutableStateFlow(mutableStateListOf<Task>())
    var tasksList = _tasksList.asStateFlow()
    private fun getTodayTask():Flow<List<Task>>{
        return flow {
            emit(repository.getTodayTasks())
        }
    }

    private fun getFutureTask():Flow<List<Task>>{
        return flow {
            emit(repository.getFutureTasks())
        }
    }
    fun getTasksList(){
        viewModelScope.launch {
            getTodayTask().zip(getFutureTask()){
                todayTask,futureTask ->
                return@zip todayTask + futureTask
            }.flowOn(Dispatchers.IO)
                .catch {  }
                .collect{
                    _tasksList.value = it.toMutableStateList()
                }
        }
    }

    fun markTaskComplete(index:Int,task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            task.isCompleted = true
            repository.updateTask(task)
            _tasksList.value[index] = task
        }
    }
}