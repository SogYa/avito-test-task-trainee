package ru.sogya.avito.avito_test_task_trainee.home.domain

import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie

interface HomeRepository {
    suspend fun getMoviesByParams(
        page: Int,
        pageSize: Int,
        ageRating: List<String>?,
        countries: List<String>?,
        year: List<String>?
    ): List<Movie>
}