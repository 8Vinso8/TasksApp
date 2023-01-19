package com.vinso.tasksapp.data.list

import kotlinx.coroutines.flow.Flow

class TaskListRepositoryImpl(
    private val dao: TaskListDao
): TaskListRepository {
    override suspend fun insertTaskList(taskList: TaskList) {
        dao.insertTaskList(taskList)
    }

    override suspend fun deleteTaskList(taskList: TaskList) {
        dao.deleteTaskList(taskList)
    }

    override suspend fun getTaskListById(id: Int): TaskList? {
        return dao.getTaskListById(id)
    }

    override fun getTaskLists(): Flow<List<TaskList>> {
        return dao.getLists()
    }

}