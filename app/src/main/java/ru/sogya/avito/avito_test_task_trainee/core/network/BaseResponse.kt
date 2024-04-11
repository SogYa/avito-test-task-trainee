package ru.sogya.avito.avito_test_task_trainee.core.network

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("docs")
    val docs: List<T>
)