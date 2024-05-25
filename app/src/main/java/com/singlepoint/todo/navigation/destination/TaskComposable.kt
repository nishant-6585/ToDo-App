package com.singlepoint.todo.navigation.destination

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.singlepoint.todo.ui.screens.task.TaskScreen
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.Constants
import com.singlepoint.todo.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
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
            Log.d("taskId", taskId.toString())
            TaskScreen(taskId = taskId, navigateToListScreen = navigateToListScreen)
            /*LaunchedEffect(key1 = taskId) {
                sharedViewModel.getSelectedTask(taskId = taskId)
            }*/
        }
    )
}