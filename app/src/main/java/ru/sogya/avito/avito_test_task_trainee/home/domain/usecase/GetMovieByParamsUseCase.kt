package ru.sogya.avito.avito_test_task_trainee.home.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Movie
import ru.sogya.avito.avito_test_task_trainee.home.data.MoviesPaggingSource
import ru.sogya.avito.avito_test_task_trainee.home.data.api.request.MovieRequestData
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository

interface GetMovieByParamsUseCase : (Int, Int, MovieRequestData?) -> Flow<PagingData<Movie>>
class GetMovieByParamsUseCaseImpl(private val homeRepository: HomeRepository) :
    GetMovieByParamsUseCase {

    companion object {
        private const val INITIAL_PAGE_KEY = 1
        private const val PAGE_SIZE = 20
    }

    override fun invoke(
        page: Int,
        pageSize: Int,
        movieRequest: MovieRequestData?
    ): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        initialKey = INITIAL_PAGE_KEY,
        pagingSourceFactory = { MoviesPaggingSource(homeRepository) }
    ).flow.flowOn(Dispatchers.IO)
}