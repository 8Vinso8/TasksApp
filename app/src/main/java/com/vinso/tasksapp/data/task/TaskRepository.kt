package com.vinso.tasksapp.data.task

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun getTaskById(id: Int): Task?

    fun getTasks(): Flow<List<Task>>

    fun getFavouriteTasks(): Flow<List<Task>>

    fun getDoneTasks(): Flow<List<Task>>

    fun getUndoneTasks(): Flow<List<Task>>
}