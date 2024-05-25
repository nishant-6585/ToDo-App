package com.singlepoint.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.singlepoint.todo.navigation.destination.listComposable
import com.singlepoint.todo.navigation.destination.taskComposable
import com.singlepoint.todo.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screens = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screens.task
        )

        taskComposable(
            navigateToListScreen = screens.list
        )
    }
}