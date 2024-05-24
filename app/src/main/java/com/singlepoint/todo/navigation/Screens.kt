package com.singlepoint.todo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN){ inclusive = true }
        }
    }

    val task: (Int) -> Unit = {taskId ->
        navController.navigate(route = "task/$taskId")
    }
}