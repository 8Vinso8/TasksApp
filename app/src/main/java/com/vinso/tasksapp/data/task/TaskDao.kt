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

    @Query("SELECT * FROM tasks WHERE isSub = 0")
    fun getMainTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isSub = 1 AND mainId = :id")
    fun getSubTasks(id: Int): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isFavourite = 1")
    fun getFavouriteTasks(): Flow<List<Task>>
}