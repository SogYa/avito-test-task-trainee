package ru.sogya.avito.avito_test_task_trainee.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sogya.avito.avito_test_task_trainee.search.data.SearchRepositoryImpl
import ru.sogya.avito.avito_test_task_trainee.search.data.api.SearchApi
import ru.sogya.avito.avito_test_task_trainee.search.data.database.SearchDao
import ru.sogya.avito.avito_test_task_trainee.search.domain.SearchRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchRepositoryModule {
    @Provides
    @Singleton
    fun provideSearchRepository(searchDao: SearchDao, searchApi: SearchApi): SearchRepository =
        SearchRepositoryImpl(searchDao, searchApi)
}