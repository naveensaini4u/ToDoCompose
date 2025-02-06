package com.naveen.saini.todocompose.ui.screens.homescreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naveen.saini.todocompose.R
import com.naveen.saini.todocompose.data.model.Task
import com.naveen.saini.todocompose.ui.componnets.Header
import com.naveen.saini.todocompose.ui.componnets.TaskListItem

@Composable
fun HomeScreen(onNavigateToAddToDo: () -> Unit) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val taskList by viewModel.tasksList.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getTasksList()
    }
    Scaffold(
        topBar = {
            Header()
        }, content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn {
                    itemsIndexed(taskList) { index, item ->
                        if (index==0 || item.isToday != taskList[index-1].isToday) {
                            Text(
                                text = if(item.isToday) stringResource(R.string.today) else stringResource(R.string.yesterday),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        TaskListItem(item, onComplete = {
                            it.isCompleted = true
                            viewModel.markTaskComplete(index,it)
                        })
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToAddToDo() },
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    )
}