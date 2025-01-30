package com.naveen.saini.todocompose.ui.screens.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.naveen.saini.todocompose.ui.componnets.Header

@Composable
fun HomeScreen(onNavigateToAddToDo:()->Unit){
    Column {
        Header()
        Button(onClick = {
            onNavigateToAddToDo()
        }) {

        }
    }
}