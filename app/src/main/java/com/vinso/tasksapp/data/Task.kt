package com.vinso.tasksapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "isDone")
    val isDone: Boolean,
    @ColumnInfo(name = "isFavourite")
    val isFavourite: Boolean,
    @ColumnInfo(name = "isSub")
    val isSubtask: Boolean = false,
    @ColumnInfo(name = "mainId")
    val MainTaskId: Int? = null,
    @ColumnInfo(name = "listId")
    val listId: Int,
    @ColumnInfo(name = "id")
    @PrimaryKey val id: Int? = null
)