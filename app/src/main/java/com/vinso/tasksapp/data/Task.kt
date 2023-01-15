package com.vinso.tasksapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val name: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)