package ru.sogya.avito.avito_test_task_trainee.film.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.sogya.avito.avito_test_task_trainee.film.data.api.MovieApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieApiModule {

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}