package com.singlepoint.todo.navigation.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
        content = { navBackStackEntry ->
            val taskId = navBackStackEntry.arguments!!.getInt(Constants.TASK_ARGUMENT_KEY)
            sharedViewModel.getSelectedTask(taskId = taskId)
            val selectedTask by sharedViewModel.selectedTask.collectAsState()

            LaunchedEffect(key1 = selectedTask) {
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }

            TaskScreen(
                selectedTask = selectedTask,
                sharedViewModel = sharedViewModel,
                navigateToListScreen = navigateToListScreen
            )
        }
    )
}