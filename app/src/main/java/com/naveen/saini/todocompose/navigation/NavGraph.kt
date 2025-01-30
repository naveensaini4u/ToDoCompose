package com.naveen.saini.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naveen.saini.todocompose.ui.screens.addtodoscreen.AddToDoScreen
import com.naveen.saini.todocompose.ui.screens.homescreen.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object AddToDoScreen

@Composable
fun ToDoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(modifier = modifier, navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen(
                onNavigateToAddToDo = {
                    navController.navigate(AddToDoScreen)
                }
            )
        }
        composable<AddToDoScreen> {
            AddToDoScreen(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}