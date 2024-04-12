package ru.sogya.avito.avito_test_task_trainee.home.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.sogya.avito.avito_test_task_trainee.core.network.BaseResponse
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.MovieData

interface HomeApi {

    @GET("movie")
    suspend fun getAllFilms(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int,
        @Query("ageRating") ageRating: List<String>?,
        @Query("countries.name") countries: List<String>?,
        @Query("year") year: List<String>?
    ): BaseResponse<MovieData>
}