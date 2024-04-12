package ru.sogya.avito.avito_test_task_trainee.film.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sogya.avito.avito_test_task_trainee.core.network.BaseResponse
import ru.sogya.avito.avito_test_task_trainee.film.data.entity.FullMovieData
import ru.sogya.avito.avito_test_task_trainee.film.data.entity.PosterData
import ru.sogya.avito.avito_test_task_trainee.film.data.entity.ReviewData

interface MovieApi {
    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") movieId: Int): FullMovieData

    @GET("review")
    suspend fun getReviewByMoviewId(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int,
        @Query("movieId") movieId: String
    ): BaseResponse<ReviewData>

    @GET("image")
    suspend fun getPostersByMovieId(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int,
        @Query("movieId") movieId: String
    ): BaseResponse<PosterData>
}