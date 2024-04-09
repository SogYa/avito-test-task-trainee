package ru.sogya.avito.avito_test_task_trainee.film.domain.entity

interface Movie {
    val id: Int
    val name: String
    val alternativeName: String
    val rating: Rating
    val description: String
    val genres: List<ResponseObject>
    val countries: List<ResponseObject>
    val ageRating: Int
    val movieLength: Int
    val year: Int
    val type: String
    val poster: Poster
    val logo: Logo
}

interface ResponseObject{
    val name: String
}