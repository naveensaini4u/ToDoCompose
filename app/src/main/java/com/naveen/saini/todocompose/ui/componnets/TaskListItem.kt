package com.naveen.saini.todocompose.ui.componnets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.naveen.saini.todocompose.data.model.Task
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TaskListItem(task: Task,onComplete:(task:Task)->Unit){
    val time = SimpleDateFormat("hh:mm a", Locale.US).format(task.time)
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = task.isCompleted, onCheckedChange = {onComplete(task)}, colors = CheckboxDefaults.colors(checkedColor = Color.Black,))
            Text(text = task.name, color = Color.Gray, fontSize = 15.sp, style = if(task.isCompleted)TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle())
        }
        Text(text =time, modifier = Modifier.align(alignment = Alignment.End),color = Color.Gray, fontSize = 10.sp )
        Spacer(modifier = Modifier.fillMaxWidth())
    }
}