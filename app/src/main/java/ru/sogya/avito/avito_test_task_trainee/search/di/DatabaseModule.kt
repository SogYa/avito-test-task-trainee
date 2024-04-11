package ru.sogya.avito.avito_test_task_trainee.search.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sogya.avito.avito_test_task_trainee.search.data.database.SearchDatabase
import ru.sogya.avito.avito_test_task_trainee.search.data.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideSearchDatabase(@ApplicationContext context: Context): SearchDatabase =
        Room.databaseBuilder(
            context,
            SearchDatabase::class.java,
            Constants.SEARCH_DATABASE
        ).build()

    @Provides
    @Singleton
    fun provideSearchDao(searchDatabase: SearchDatabase) = searchDatabase.searchDao
}