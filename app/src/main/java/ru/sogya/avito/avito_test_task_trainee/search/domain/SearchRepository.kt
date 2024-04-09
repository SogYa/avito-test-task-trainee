package ru.sogya.avito.avito_test_task_trainee.search.domain

import kotlinx.coroutines.flow.Flow
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

interface SearchRepository {
    suspend fun insertSearch(search: Search)

    suspend fun getSearchHistory(): Flow<List<Search>>
    suspend fun searchByName(name: String): Flow<List<Search>>
}