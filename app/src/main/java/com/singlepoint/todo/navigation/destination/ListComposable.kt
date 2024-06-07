package com.singlepoint.todo.navigation.destination

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.singlepoint.todo.presentaion.screens.list.ListScreen
import com.singlepoint.todo.presentaion.viewmodels.SharedViewModel
import com.singlepoint.todo.util.Constants.LIST_ARGUMENT_KEY
import com.singlepoint.todo.util.Constants.LIST_SCREEN
import com.singlepoint.todo.util.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }

        Log.d("Action", action.name)
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel
        )
    }
}