package com.singlepoint.todo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Pink80 = Color(0xFFEFB8C8)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = Color(0xFFFFFFFF)

val ColorScheme.fabBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Teal200 else Purple700

val ColorScheme.topAppBarContentColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.White else LightGray

val ColorScheme.topAppBarBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Purple500 else Color.Black