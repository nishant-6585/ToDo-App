package com.singlepoint.todo.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.singlepoint.todo.util.data.models.Priority
import com.singlepoint.todo.ui.theme.MEDIUM_PADDING
import com.singlepoint.todo.util.PriorityDropDown

@Composable
fun TaskContent(
    paddingValues: PaddingValues,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Log.d("PaddingValue", paddingValues.toString())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = MEDIUM_PADDING,
                end = MEDIUM_PADDING,
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {
                onTitleChange(it)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.title)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true
        )

        HorizontalDivider(
            modifier = Modifier.height(MEDIUM_PADDING),
            color = MaterialTheme.colorScheme.background
        )

        PriorityDropDown(priority = priority) {
            onPrioritySelected(it)
        }
        
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = {
                onDescriptionChange(it)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.description))
            }
        )
    }
}

@Composable
@Preview
fun TaskContentPreview() {
    TaskContent(
        paddingValues = PaddingValues(MEDIUM_PADDING),
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}