package com.singlepoint.todo.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.singlepoint.todo.R
import com.singlepoint.todo.ui.theme.TODO_APP_LOGO_SIZE
import com.singlepoint.todo.ui.theme.ToDoAppTheme
import com.singlepoint.todo.ui.theme.splashScreenBackground

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.splashScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(TODO_APP_LOGO_SIZE),
            painter = painterResource(id = getLogo()),
            contentDescription = stringResource(id = R.string.todo_logo)
        )
    }
}

@Composable
fun getLogo() : Int {
    return if(isSystemInDarkTheme()) {
        R.drawable.logo_dark
    } else {
        R.drawable.logo_light
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen()
}