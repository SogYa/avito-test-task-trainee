package ru.sogya.avito.avito_test_task_trainee.home.data.entity

import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Rating

data class RatingData(override val imdb: Double = 0.0) : Rating

fun Rating.toData(): RatingData = RatingData(
    imdb
)
