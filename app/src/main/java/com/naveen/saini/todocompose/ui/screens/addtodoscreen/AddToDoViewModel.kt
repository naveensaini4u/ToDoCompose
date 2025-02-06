package com.naveen.saini.todocompose.ui.screens.addtodoscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveen.saini.todocompose.data.model.Task
import com.naveen.saini.todocompose.data.repository.local.LocalDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(val repository: LocalDataRepository):ViewModel() {
    private val _taskName = MutableStateFlow("")
    val taskName = _taskName.asStateFlow()

    private val _time = MutableStateFlow("")
    val time = _time.asStateFlow()
    private var timeInMillis:Long = Calendar.getInstance().timeInMillis

    private val _isToday  = MutableStateFlow(false)
    var isToday = _isToday.asStateFlow()

    init {
        setTimeMilliseconds(Calendar.getInstance().timeInMillis)
    }

    fun addTask(){
        viewModelScope.launch{
            repository.setTask(Task(taskName.value,timeInMillis, isToday.value))
        }
    }

    fun setTaskName(name:String){
        _taskName.value = name
    }

    fun setTimeMilliseconds(timeInMillis: Long) {
        this.timeInMillis = timeInMillis
        _time.value = SimpleDateFormat("hh:mm a", Locale.US).format(timeInMillis)
    }

    fun setIsToday(isChecked:Boolean){
        _isToday.value = isChecked
    }
}