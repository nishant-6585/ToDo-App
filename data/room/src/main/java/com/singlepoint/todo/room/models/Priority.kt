package com.singlepoint.todo.room.models

import androidx.compose.ui.graphics.Color
import com.singlepoint.todo.ui.theme.HighPriorityColor
import com.singlepoint.todo.ui.theme.LowPriorityColor
import com.singlepoint.todo.ui.theme.MediumPriorityColor
import com.singlepoint.todo.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
