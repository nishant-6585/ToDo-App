package com.singlepoint.todo.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.singlepoint.todo.data.models.Priority
import com.singlepoint.todo.data.models.ToDoTask
import com.singlepoint.todo.ui.viewmodels.SharedViewModel
import com.singlepoint.todo.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

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
                title = title,
                onTitleChange = { titleText ->
                    sharedViewModel.title.value = titleText
                },
                description = description,
                onDescriptionChange = { descriptionText ->
                    sharedViewModel.description.value = descriptionText
                },
                priority = priority,
                onPrioritySelected = { priority ->
                    sharedViewModel.priority.value = priority
                }
            )

        }
    )
}