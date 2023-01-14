package com.vinso.tasksapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("Select * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Query("Select * FROM task")
    fun getTasks(): Flow<List<Task>>
}