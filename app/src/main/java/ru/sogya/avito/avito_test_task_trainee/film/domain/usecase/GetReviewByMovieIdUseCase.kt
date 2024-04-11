package ru.sogya.avito.avito_test_task_trainee.film.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.sogya.avito.avito_test_task_trainee.film.data.ReviewPagingSource
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review

interface GetReviewByMovieIdUseCase : suspend (Int) -> Flow<PagingData<Review>>

class GetReviewByMovieIdUseCaseImpl(private val movieRepository: MovieRepository) :
    GetReviewByMovieIdUseCase {
    private companion object {
        private const val INITIAL_PAGE_KEY = 1
        private const val PAGE_SIZE = 10
    }

    override suspend fun invoke(movieId: Int): Flow<PagingData<Review>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            initialKey = INITIAL_PAGE_KEY,
            pagingSourceFactory = { ReviewPagingSource(movieId, movieRepository) }
        ).flow.flowOn(Dispatchers.IO)
}