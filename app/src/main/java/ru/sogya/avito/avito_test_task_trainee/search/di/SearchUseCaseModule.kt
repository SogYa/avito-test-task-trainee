package ru.sogya.avito.avito_test_task_trainee.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sogya.avito.avito_test_task_trainee.search.domain.SearchRepository
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.GetAllSearchesHistoryUseCase
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.GetAllSearchesHistoryUseCaseImpl
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.InsertSearchUseCase
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.InsertSearchUseCaseImpl
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.SearchByNameUseCase
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.SearchByNameUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class SearchUseCaseModule {
    @Provides
    fun provideInsertSearchUseCase(searchRepository: SearchRepository): InsertSearchUseCase =
        InsertSearchUseCaseImpl(searchRepository)

    @Provides
    fun provideGetAllSearchesUseCase(searchRepository: SearchRepository): GetAllSearchesHistoryUseCase =
        GetAllSearchesHistoryUseCaseImpl(searchRepository)

    @Provides
    fun provideSearchByNameUseCase(searchRepository: SearchRepository): SearchByNameUseCase =
        SearchByNameUseCaseImpl(searchRepository)
}