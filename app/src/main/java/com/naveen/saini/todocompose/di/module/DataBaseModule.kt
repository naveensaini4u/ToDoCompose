package com.naveen.saini.todocompose.di.module

import android.content.Context
import androidx.room.Room
import com.naveen.saini.todocompose.data.datastore.local.TODOAppDatabase
import com.naveen.saini.todocompose.data.datastore.local.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun getTaskDatabase(@ApplicationContext context:Context):TODOAppDatabase {
        return Room.databaseBuilder(context,TODOAppDatabase::class.java,"todo.db").build()
    }

    @Provides
    @Singleton
    fun getTaskDao(database: TODOAppDatabase):TaskDao = database.getTaskDao()
}