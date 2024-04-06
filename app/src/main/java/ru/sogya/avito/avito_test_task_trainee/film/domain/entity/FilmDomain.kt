package ru.sogya.avito.avito_test_task_trainee.film.domain.entity

interface FilmDomain {
    val id: Int
    val name: String
    val alternativeName: String
    val rating: Double
    val description: String
    val genres: List<String>
    val countries: List<String>
    val ageRating: Int
    val movieLength: Int
    val year: Int
    val type: String
    val poster: Poster
    val logo: Logo
}