package com.naveen.saini.todocompose.ui.screens.addtodoscreen

import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveen.saini.todocompose.data.model.Task
import com.naveen.saini.todocompose.data.repository.local.LocalDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(val repository: LocalDataRepository):ViewModel() {
    var taskName = mutableStateOf("Hello")
    var checked  = mutableStateOf(true)
    fun addTask(){
        viewModelScope.launch{
            repository.setTask(Task(name="Task",time=1738235531, isToday = true))
        }
    }
}