package ru.sogya.avito.avito_test_task_trainee.film.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetMovieByIdUseCase
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetMovieByIdUseCaseImpl
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetPostersByMovieIdUseCase
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetPostersByMovieIdUseCaseImpl
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetReviewByMovieIdUseCase
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetReviewByMovieIdUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class MovieUseCaseModule {
    @Provides
    fun provideGetMovieByIdUseCase(movieRepository: MovieRepository): GetMovieByIdUseCase =
        GetMovieByIdUseCaseImpl(movieRepository)

    @Provides
    fun provideGetReviewByMovieIdUseCase(movieRepository: MovieRepository): GetReviewByMovieIdUseCase =
        GetReviewByMovieIdUseCaseImpl(movieRepository)

    @Provides
    fun provideGetPostersByMovieIdUseCase(movieRepository: MovieRepository): GetPostersByMovieIdUseCase =
        GetPostersByMovieIdUseCaseImpl(movieRepository)
}