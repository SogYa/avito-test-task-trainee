package ru.sogya.avito.avito_test_task_trainee.film.domain.entity

import ru.sogya.avito.avito_test_task_trainee.core.network.ResponseObject
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Rating

interface FullMovie {
    val id: Int
    val type: String
    val name: String
    val description: String
    val rating: Rating
    val poster: Poster
    val genres: List<ResponseObject>
    val countries: List<ResponseObject>
    val persons: List<Person>
    val backdrop: Poster
}