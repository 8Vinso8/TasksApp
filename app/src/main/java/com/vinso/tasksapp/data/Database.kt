package com.vinso.tasksapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vinso.tasksapp.data.task.Task
import com.vinso.tasksapp.data.task.TaskDao

@Database(
    entities = [Task::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val taskDao: TaskDao
}