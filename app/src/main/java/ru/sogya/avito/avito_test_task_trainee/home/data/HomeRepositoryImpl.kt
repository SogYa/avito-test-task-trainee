package ru.sogya.avito.avito_test_task_trainee.home.data

import ru.sogya.avito.avito_test_task_trainee.home.data.api.HomeApi
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie

class HomeRepositoryImpl(private val api: HomeApi) : HomeRepository {
    override suspend fun getMoviesByParams(
        page: Int,
        pageSize: Int,
        ageRating: List<String>?,
        countries: List<String>?,
        year: List<String>?
    ): List<Movie> {
        return api.getAllFilms(page, pageSize, ageRating, countries, year).docs
    }
}