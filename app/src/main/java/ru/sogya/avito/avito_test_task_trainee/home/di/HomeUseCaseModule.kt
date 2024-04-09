package ru.sogya.avito.avito_test_task_trainee.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository
import ru.sogya.avito.avito_test_task_trainee.home.domain.usecase.GetMovieByParamsUseCase
import ru.sogya.avito.avito_test_task_trainee.home.domain.usecase.GetMovieByParamsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class HomeUseCaseModule {

    @Provides
    fun provideGetMoviewByParamsUseCase(homeRepository: HomeRepository): GetMovieByParamsUseCase =
        GetMovieByParamsUseCaseImpl(homeRepository)
}