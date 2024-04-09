package ru.sogya.avito.avito_test_task_trainee.home.data.api.request

import com.google.gson.annotations.SerializedName
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.MovieRequest

data class MovieRequestData(
    @SerializedName("year")
    override val year: List<String> = listOf(),
    @SerializedName("ageRating")
    override val ageRating: List<String> = listOf(),
    @SerializedName("countries.name")
    override val countries: List<String> = listOf()
) : MovieRequest

fun MovieRequest.toData() = MovieRequestData(
    year, ageRating, countries
)
