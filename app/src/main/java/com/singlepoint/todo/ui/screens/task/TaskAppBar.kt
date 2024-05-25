package com.singlepoint.todo.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.singlepoint.todo.R
import com.singlepoint.todo.data.models.Priority
import com.singlepoint.todo.data.models.ToDoTask
import com.singlepoint.todo.ui.theme.topAppBarBackgroundColor
import com.singlepoint.todo.ui.theme.topAppBarContentColor
import com.singlepoint.todo.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask? = null,
    navigateToListScreen: (Action) -> Unit
) {
    if(selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClick = navigateToListScreen)
        },
        title = {
            Text(text = "Add Task")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor, // Set the background color here
            titleContentColor = MaterialTheme.colorScheme.topAppBarContentColor // Set the title content color here
        ),
        actions = {
            AddAction {

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClick = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colorScheme.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor, // Set the background color here
            titleContentColor = MaterialTheme.colorScheme.topAppBarContentColor // Set the title content color here
        ),
        actions = {
            DeleteAction {
                navigateToListScreen
            }
            UpdateAction {
                navigateToListScreen
            }

        }
    )
}

@Composable
fun BackAction(
    onBackClick: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onBackClick.invoke(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun CloseAction(
    onCloseClick: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onCloseClick.invoke(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClick: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onDeleteClick.invoke(Action.DELETE)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClick: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onUpdateClick.invoke(Action.UPDATE)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClick: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onAddClick.invoke(Action.ADD)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
@Preview
fun TaskAppBarPreview() {
    TaskAppBar {

    }
}

@Composable
@Preview
fun ExistingAppBarPreview() {
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            id = 1,
            title = "Title",
            description = "Description",
            priority = Priority.LOW
        )
    ) {
        
    }
}