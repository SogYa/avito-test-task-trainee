package ru.sogya.avito.avito_test_task_trainee.film.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.sogya.avito.avito_test_task_trainee.film.data.api.MovieApi
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.FullMovie
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Poster
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review

class MovieRepositoryImpl(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getMovieById(movieId: Int): Flow<FullMovie> = flow {
        emit(movieApi.getMovieById(movieId))
    }.flowOn(Dispatchers.IO)

    override suspend fun getReviewByMovieId(page: Int, pageSize: Int, movieId: Int): List<Review> =
        movieApi.getReviewByMoviewId(
            page = page,
            pageSize = pageSize,
            movieId = movieId.toString()
        ).docs

    override suspend fun getPostersByMovieId(page: Int, pageSize: Int, movieId: Int): List<Poster> =
        movieApi.getPostersByMovieId(
            page = page,
            pageSize = pageSize,
            movieId = movieId.toString()
        ).docs
}