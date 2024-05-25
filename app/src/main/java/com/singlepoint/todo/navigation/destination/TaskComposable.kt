package com.singlepoint.todo.navigation.destination

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.singlepoint.todo.ui.screens.task.TaskScreen
import com.singlepoint.todo.ui.viewmodels.SharedViewModel
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.Constants
import com.singlepoint.todo.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (action: Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        deepLinks = emptyList(),
        content = { navBackStackEntry ->
            val taskId = navBackStackEntry.arguments!!.getInt(Constants.TASK_ARGUMENT_KEY)

            sharedViewModel.getSelectedTask(taskId = taskId)
            val selectedTask = sharedViewModel.selectedTask.collectAsState()

            TaskScreen(
                selectedTask = selectedTask.value,
                navigateToListScreen = navigateToListScreen
            )
        }
    )
}