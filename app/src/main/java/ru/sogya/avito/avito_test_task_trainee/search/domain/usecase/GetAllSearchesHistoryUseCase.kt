package ru.sogya.avito.avito_test_task_trainee.search.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.sogya.avito.avito_test_task_trainee.search.domain.SearchRepository
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

interface GetAllSearchesHistoryUseCase : suspend () -> Flow<List<Search>>

class GetAllSearchesHistoryUseCaseImpl(private val searchRepository: SearchRepository) :
    GetAllSearchesHistoryUseCase {
    override suspend fun invoke(): Flow<List<Search>> = searchRepository.getSearchHistory()
}