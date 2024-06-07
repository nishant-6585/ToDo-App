package com.singlepoint.todo.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.singlepoint.todo.presentaion.screens.splash.SplashScreen
import com.singlepoint.todo.util.Constants.SPLASH_SCREEN

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {

    composable(
        route = SPLASH_SCREEN,
    ) {
        SplashScreen(navigateToListScreen)
    }
}