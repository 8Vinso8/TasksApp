package com.vinso.tasksapp.data.list

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(taskList: TaskList)

    @Delete
    suspend fun deleteTaskList(taskList: TaskList)

    @Query("SELECT * FROM lists WHERE id = :id")
    suspend fun getTaskListById(id: Int): TaskList?

    @Query("SELECT * FROM lists")
    fun getLists(): Flow<List<TaskList>>
}