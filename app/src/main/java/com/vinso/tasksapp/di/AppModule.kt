package com.vinso.tasksapp.di

import android.app.Application
import androidx.room.Room
import com.vinso.tasksapp.data.Database
import com.vinso.tasksapp.data.list.TaskListRepository
import com.vinso.tasksapp.data.list.TaskListRepositoryImpl
import com.vinso.tasksapp.data.task.TaskRepository
import com.vinso.tasksapp.data.task.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "tasks_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: Database): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }
    
    @Provides
    @Singleton
    fun provideTaskListRepository(db: Database): TaskListRepository {
        return TaskListRepositoryImpl(db.taskListDao)
    }
}