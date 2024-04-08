package ru.sogya.avito.avito_test_task_trainee.home.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.sogya.avito.avito_test_task_trainee.home.data.api.request.MovieRequestData
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.MoviesResponse

interface HomeApi {

    @GET("movie")
    suspend fun getAllFilms(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int,
        @Query("") movieRequestData: MovieRequestData?,
    ): MoviesResponse
}