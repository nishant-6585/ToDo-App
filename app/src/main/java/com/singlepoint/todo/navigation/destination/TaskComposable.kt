package com.singlepoint.todo.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (action: Action) -> Unit
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        deepLinks = emptyList(),
        content = {

        }
    )
}