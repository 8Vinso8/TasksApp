package com.vinso.tasksapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lists")
data class List(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id") @PrimaryKey val id: Int? = null
)