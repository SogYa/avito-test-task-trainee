package ru.sogya.avito.avito_test_task_trainee.search.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.sogya.avito.avito_test_task_trainee.core.network.BaseResponse
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

interface SearchApi {
    @GET("movie/search")
    suspend fun getMovieByName(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("query") query: String,
    ): BaseResponse<Search>
}