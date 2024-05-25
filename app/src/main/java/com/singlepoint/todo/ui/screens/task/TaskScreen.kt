package com.singlepoint.todo.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.singlepoint.todo.data.models.Priority
import com.singlepoint.todo.data.models.ToDoTask
import com.singlepoint.todo.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        content = {
            TaskContent(
                it,
                title = "",
                onTitleChange = {},
                description = "",
                onDescriptionChange = {},
                priority = Priority.LOW,
                onPrioritySelected = {}
            )

        }
    )
}