package ru.sogya.avito.avito_test_task_trainee.film.domain

import kotlinx.coroutines.flow.Flow
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.FullMovie
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Poster
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review

interface MovieRepository {
    suspend fun getMovieById(movieId: Int): Flow<FullMovie>

    suspend fun getReviewByMovieId(page: Int, pageSize: Int, movieId: Int): List<Review>

    suspend fun getPostersByMovieId(page: Int, pageSize: Int, movieId: Int): List<Poster>
}