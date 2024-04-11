package ru.sogya.avito.avito_test_task_trainee.film.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.sogya.avito.avito_test_task_trainee.film.data.PosterPagingSource
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Poster

interface GetPostersByMovieIdUseCase : suspend (Int) -> Flow<PagingData<Poster>>

class GetPostersByMovieIdUseCaseImpl(private val movieRepository: MovieRepository) :
    GetPostersByMovieIdUseCase {
    private companion object {
        private const val INITIAL_PAGE_KEY = 1
        private const val PAGE_SIZE = 10
    }

    override suspend fun invoke(movieId: Int): Flow<PagingData<Poster>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            initialKey = INITIAL_PAGE_KEY,
            pagingSourceFactory = { PosterPagingSource(movieId, movieRepository) }
        ).flow.flowOn(Dispatchers.IO)
}