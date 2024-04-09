package ru.sogya.avito.avito_test_task_trainee.home.data.entity

import com.google.gson.annotations.SerializedName
import ru.sogya.avito.avito_test_task_trainee.film.data.entity.FilmData

data class MoviesResponse(
    @SerializedName("docs")
    val docs: List<FilmData>
)
