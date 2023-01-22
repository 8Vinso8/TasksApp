package com.vinso.tasksapp.data.task

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isDone = 1")
    fun getDoneTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isDone = 0")
    fun getUndoneTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isFavourite = 1")
    fun getFavouriteTasks(): Flow<List<Task>>
}