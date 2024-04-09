package ru.sogya.avito.avito_test_task_trainee.search.domain.usecase

import ru.sogya.avito.avito_test_task_trainee.search.domain.SearchRepository
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

interface InsertSearchUseCase : suspend (Search) -> Unit

class InsertSearchUseCaseImpl(private val searchRepository: SearchRepository) :
    InsertSearchUseCase {
    override suspend fun invoke(search: Search) {
        searchRepository.insertSearch(search)
    }
}