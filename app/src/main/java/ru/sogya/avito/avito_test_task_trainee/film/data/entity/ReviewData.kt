package ru.sogya.avito.avito_test_task_trainee.film.data.entity

import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review

data class ReviewData(
    override val type: String,
    override val author: String,
    override val title: String,
    override val review: String
) : Review