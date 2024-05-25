package com.singlepoint.todo.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.singlepoint.todo.R
import com.singlepoint.todo.ui.theme.topAppBarBackgroundColor
import com.singlepoint.todo.ui.theme.topAppBarContentColor
import com.singlepoint.todo.util.Action

@Composable
fun TaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    NewTaskAppBar(navigateToListScreen = navigateToListScreen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction (onBackClick = navigateToListScreen)
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
            contentDescription = stringResource(id = R.string.back_arrow),
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