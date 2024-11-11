package com.ctp.taskmanageapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ctp.taskmanageapp.common.utils.Constants.APPLICATION_ID
import com.ctp.taskmanageapp.data.local.dao.TaskInfoDao
import com.ctp.taskmanageapp.data.local.dbstore.TaskInfoDatabase
import com.ctp.taskmanageapp.data.repositories.SharedRepository
import com.ctp.taskmanageapp.data.repositories.TaskInfoRepository
import com.ctp.taskmanageapp.domain.repository.ISharedRepository
import com.ctp.taskmanageapp.domain.repository.ITaskInfoRepository
import com.ctp.taskmanageapp.domain.usecase.SharedUseCases
import com.ctp.taskmanageapp.domain.usecase.TaskCalculationsUseCases
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
    fun providesSharedPreferences(
        @ApplicationContext application: Context
    ): SharedPreferences = application.getSharedPreferences(
        "${APPLICATION_ID}.shared_app",
        Context.MODE_PRIVATE
    )

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
    fun providesTaskInfoRepository(dao: TaskInfoDao): ITaskInfoRepository {
        return TaskInfoRepository(dao)
    }

    @Provides
    fun provideTaskUseCases(taskInfoRepository: ITaskInfoRepository): TaskInfoUseCases {
        return TaskInfoUseCases(taskInfoRepository)
    }

    @Provides
    fun provideTaskCalculationsUseCases() : TaskCalculationsUseCases {
        return TaskCalculationsUseCases()
    }

    @Provides
    @Singleton
    fun providesSharedRepository(sharedPrefer: SharedPreferences): ISharedRepository {
        return SharedRepository(sharedPrefer)
    }

    @Provides
    fun provideSharedUseCases(sharedRepository: ISharedRepository): SharedUseCases {
        return SharedUseCases(sharedRepository)
    }
}
