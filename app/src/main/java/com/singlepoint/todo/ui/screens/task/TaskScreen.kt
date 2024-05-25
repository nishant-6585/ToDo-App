package com.singlepoint.todo.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.singlepoint.todo.util.Action

@Composable
fun TaskScreen(
    taskId: Int,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        },
        content = {
            it.hashCode()
        }
    )
}