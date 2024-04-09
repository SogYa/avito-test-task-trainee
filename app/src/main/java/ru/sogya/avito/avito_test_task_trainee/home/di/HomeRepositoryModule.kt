package ru.sogya.avito.avito_test_task_trainee.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sogya.avito.avito_test_task_trainee.home.data.HomeRepositoryImpl
import ru.sogya.avito.avito_test_task_trainee.home.data.api.HomeApi
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeRepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(api: HomeApi): HomeRepository = HomeRepositoryImpl(api)
}