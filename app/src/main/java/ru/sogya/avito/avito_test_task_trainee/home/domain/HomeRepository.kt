package ru.sogya.avito.avito_test_task_trainee.home.domain

import kotlinx.coroutines.flow.Flow
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Movie
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.MovieRequest

interface HomeRepository {
    suspend fun getMoviesByParams(
        page: Int,
        pageSize: Int,
        request: MovieRequest?
    ): List<Movie>
}