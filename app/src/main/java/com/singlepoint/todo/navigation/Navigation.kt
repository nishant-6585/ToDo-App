package com.singlepoint.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.singlepoint.todo.navigation.destination.listComposable
import com.singlepoint.todo.navigation.destination.splashComposable
import com.singlepoint.todo.navigation.destination.taskComposable
import com.singlepoint.todo.ui.viewmodels.SharedViewModel
import com.singlepoint.todo.util.Constants.LIST_SCREEN
import com.singlepoint.todo.util.Constants.SPLASH_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screens = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToListScreen = screens.splash
        )

        listComposable(
            navigateToTaskScreen = screens.task,
            sharedViewModel
        )

        taskComposable(
            navigateToListScreen = screens.list,
            sharedViewModel = sharedViewModel
        )
    }
}