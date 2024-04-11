package ru.sogya.avito.avito_test_task_trainee.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.sogya.avito.avito_test_task_trainee.search.data.api.SearchApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchApiModule {
    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)
}