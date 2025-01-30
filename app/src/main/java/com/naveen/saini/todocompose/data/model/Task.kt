package com.naveen.saini.todocompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    val name:String,
    val time:Long,
    val isToday:Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null;
}
