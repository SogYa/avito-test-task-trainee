package ru.sogya.avito.avito_test_task_trainee.home.data.entity

import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Poster

data class PosterData(override val url: String = "", override val previewUrl: String = "") : Poster

fun Poster.toData(): PosterData = PosterData(
    url, previewUrl
)