package ru.sogya.avito.avito_test_task_trainee.search.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.sogya.avito.avito_test_task_trainee.search.domain.SearchRepository
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search


interface SearchByNameUseCase : suspend (String) -> Flow<List<Search>>

class SearchByNameUseCaseImpl(private val searchRepository: SearchRepository) :
    SearchByNameUseCase {
    override suspend fun invoke(name: String): Flow<List<Search>> =
        searchRepository.searchByName(name = name)
}