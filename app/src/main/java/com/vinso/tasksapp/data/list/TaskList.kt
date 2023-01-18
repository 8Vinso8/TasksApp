package com.vinso.tasksapp.data.list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lists")
data class TaskList(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id") @PrimaryKey val id: Int? = null
)