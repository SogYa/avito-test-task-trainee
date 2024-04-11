package ru.sogya.avito.avito_test_task_trainee.film.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sogya.avito.avito_test_task_trainee.film.data.MovieRepositoryImpl
import ru.sogya.avito.avito_test_task_trainee.film.data.api.MovieApi
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: MovieApi): MovieRepository = MovieRepositoryImpl(movieApi)
}