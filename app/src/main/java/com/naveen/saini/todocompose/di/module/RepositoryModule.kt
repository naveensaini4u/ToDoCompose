package com.naveen.saini.todocompose.di.module

import com.naveen.saini.todocompose.data.datastore.local.dao.TaskDao
import com.naveen.saini.todocompose.data.repository.local.LocalDataRepository
import com.naveen.saini.todocompose.data.repository.local.LocalDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun getTaskRepository(taskDao: TaskDao):LocalDataRepository = LocalDataRepositoryImpl(taskDao)
}