package com.vinso.tasksapp.di

import android.app.Application
import androidx.room.Room
import com.vinso.tasksapp.data.Database
import com.vinso.tasksapp.data.TaskRepository
import com.vinso.tasksapp.data.TaskRepositoryImpl
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
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: Database): TaskRepository {
        return TaskRepositoryImpl(db.dao)
    }
}