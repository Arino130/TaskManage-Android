package com.ctp.taskmanageapp.di

import android.content.Context
import androidx.room.Room
import com.ctp.taskmanageapp.data.local.dao.TaskInfoDao
import com.ctp.taskmanageapp.data.local.dbstore.TaskInfoDatabase
import com.ctp.taskmanageapp.data.repositories.TaskInfoRepository
import com.ctp.taskmanageapp.domain.repository.TaskInfoRepositoryInterface
import com.ctp.taskmanageapp.domain.usecase.TaskInfoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocalDatabase(@ApplicationContext context: Context): TaskInfoDatabase {
        return Room.databaseBuilder(context, TaskInfoDatabase::class.java, "local_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesTaskInfoDao(db: TaskInfoDatabase): TaskInfoDao {
        return db.taskInfoDao()
    }

    @Provides
    @Singleton
    fun providesTaskInfoRepository(dao: TaskInfoDao): TaskInfoRepositoryInterface {
        return TaskInfoRepository(dao)
    }

    @Provides
    fun provideTaskUseCases(taskInfoRepository: TaskInfoRepositoryInterface): TaskInfoUseCases {
        return TaskInfoUseCases(taskInfoRepository)
    }
}
