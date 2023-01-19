package com.vinso.tasksapp.data.list

import kotlinx.coroutines.flow.Flow

interface TaskListRepository {
    suspend fun insertTaskList(taskList: TaskList)

    suspend fun deleteTaskList(taskList: TaskList)

    suspend fun getTaskListById(id: Int): TaskList?

    fun getTaskLists(): Flow<List<TaskList>>
}