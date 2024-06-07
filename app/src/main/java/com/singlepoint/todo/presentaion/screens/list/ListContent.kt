package com.singlepoint.todo.presentaion.screens.list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singlepoint.todo.R
import com.singlepoint.todo.data.models.Priority
import com.singlepoint.todo.data.models.ToDoTask
import com.singlepoint.todo.ui.theme.HighPriorityColor
import com.singlepoint.todo.ui.theme.LARGEST_PADDING
import com.singlepoint.todo.ui.theme.LARGE_PADDING
import com.singlepoint.todo.ui.theme.PRIORITY_INDICATOR_SIZE
import com.singlepoint.todo.ui.theme.TASK_ITEM_ELEVATION
import com.singlepoint.todo.ui.theme.taskItemBackgroundColor
import com.singlepoint.todo.ui.theme.taskItemTextColor
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.RequestState
import com.singlepoint.todo.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    paddingValues: PaddingValues,
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        paddingValues = paddingValues,
                        onSwipeToDelete = onSwipeToDelete,
                        task = searchedTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }

            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        paddingValues = paddingValues,
                        onSwipeToDelete = onSwipeToDelete,
                        task = allTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }

            sortState.data == Priority.LOW -> {
                HandleListContent(
                    paddingValues = paddingValues,
                    onSwipeToDelete = onSwipeToDelete,
                    task = lowPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    paddingValues = paddingValues,
                    onSwipeToDelete = onSwipeToDelete,
                    task = highPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
        }
    }
}

@Composable
fun HandleListContent(
    paddingValues: PaddingValues,
    task: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Log.d("HandleListContent", "ListContent -> task: $task")
    if (task.isEmpty()) {
        EmptyContent(paddingValues = paddingValues)
    } else {
        DisplayTask(
            paddingValues = paddingValues,
            onSwipeToDelete = onSwipeToDelete,
            tasks = task,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTask(
    paddingValues: PaddingValues,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Log.d("DisplayTask", "DisplayTask: $tasks")
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->

            val dismissState = rememberSwipeToDismissBoxState()
            val scope = rememberCoroutineScope()
            when (dismissState.currentValue) {
                SwipeToDismissBoxValue.EndToStart -> {
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(Action.DELETE, task)
                    }
                }

                SwipeToDismissBoxValue.StartToEnd -> {

                }

                SwipeToDismissBoxValue.Settled -> {
                }
            }

            val degrees by animateFloatAsState(
                if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart)
                    0f
                else
                    -45f,
                label = ""
            )

            val itemAppeared = remember { mutableStateOf(false) }
            LaunchedEffect(
                key1 = true
            ) {
                itemAppeared.value = true
            }

            AnimatedVisibility(
                visible = itemAppeared.value,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        300
                    )
                )
            ) {
                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromEndToStart = true,
                    backgroundContent = { RedBackground(degrees = degrees) },
                    content = {
                        TaskItem(
                            toDoTask = task,
                            navigateToTaskScreen = navigateToTaskScreen
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.TopEnd,
        content = {
            Icon(
                modifier = Modifier.rotate(degrees = degrees),
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(id = R.string.delete_icon),
                tint = Color.White
            )
        }
    )
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        color = MaterialTheme.colorScheme.taskItemBackgroundColor,
        shape = RectangleShape,
        shadowElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.taskItemTextColor,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colorScheme.taskItemTextColor,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            id = 0,
            title = "Title",
            description = "Description",
            priority = Priority.HIGH
        ),
        navigateToTaskScreen = {}
    )
}

@Composable
@Preview
fun RedBackgroundPreview() {
    Column(
        modifier = Modifier.height(100.dp)
    ) {
        RedBackground(degrees = 0f)
    }

}