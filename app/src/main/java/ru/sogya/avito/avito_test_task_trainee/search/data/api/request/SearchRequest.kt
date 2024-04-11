package ru.sogya.avito.avito_test_task_trainee.search.data.api.request

import com.google.gson.annotations.SerializedName

data class SearchRequest(
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("limit")
    val pageSize: Int = 20,
    @SerializedName("query")
    val query: String = ""
)
