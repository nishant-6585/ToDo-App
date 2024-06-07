package com.singlepoint.todo.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.singlepoint.todo.util.Constants.DATABASE_TABLE
import com.singlepoint.todo.util.data.models.Priority

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
