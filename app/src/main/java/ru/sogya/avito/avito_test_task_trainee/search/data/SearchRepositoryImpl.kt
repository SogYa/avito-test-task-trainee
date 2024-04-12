package ru.sogya.avito.avito_test_task_trainee.search.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.sogya.avito.avito_test_task_trainee.search.data.api.SearchApi
import ru.sogya.avito.avito_test_task_trainee.search.data.database.SearchDao
import ru.sogya.avito.avito_test_task_trainee.search.data.entity.toData
import ru.sogya.avito.avito_test_task_trainee.search.domain.SearchRepository
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

class SearchRepositoryImpl(
    private val searchDao: SearchDao,
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun insertSearch(search: Search) {
        searchDao.insertSearch(search.toData())
    }

    override suspend fun getSearchHistory(): Flow<List<Search>> =
        searchDao.getAllSearches().flowOn(Dispatchers.IO)

    override suspend fun searchByName(name: String): Flow<List<Search>> = flow {
        emit(searchApi.getMovieByName(query = name).docs)
    }.flowOn(Dispatchers.IO)
}