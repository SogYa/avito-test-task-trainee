package ru.sogya.avito.avito_test_task_trainee.search.domain.entity

import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Poster
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Rating

interface Search {
    val id: Int
    val name: String
    val alternativeName: String
    val rating: Rating
    val year: Int
    val poster: Poster
}