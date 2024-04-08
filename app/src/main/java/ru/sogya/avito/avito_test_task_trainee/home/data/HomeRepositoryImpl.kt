package ru.sogya.avito.avito_test_task_trainee.home.data

import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Movie
import ru.sogya.avito.avito_test_task_trainee.home.data.api.HomeApi
import ru.sogya.avito.avito_test_task_trainee.home.data.api.request.toData
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.MovieRequest

class HomeRepositoryImpl(private val api: HomeApi) : HomeRepository {
    override suspend fun getMoviesByParams(
        page: Int,
        pageSize: Int,
        request: MovieRequest?
    ): List<Movie>{
        return api.getAllFilms(page, pageSize, request?.toData()).docs
    }
}