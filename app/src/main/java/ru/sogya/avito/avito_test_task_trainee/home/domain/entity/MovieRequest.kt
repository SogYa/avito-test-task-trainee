package ru.sogya.avito.avito_test_task_trainee.home.domain.entity

interface MovieRequest {
    val year: List<String>
    val ageRating: List<String>
    val countries: List<String>
}