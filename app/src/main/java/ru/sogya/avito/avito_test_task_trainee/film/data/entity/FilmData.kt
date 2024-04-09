package ru.sogya.avito.avito_test_task_trainee.film.data.entity

import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Movie
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.ResponseObject

data class FilmData(
    override val id: Int,
    override val name: String,
    override val alternativeName: String,
    override val rating: RatingData,
    override val description: String,
    override val genres: List<ResponseObjectData>,
    override val countries: List<ResponseObjectData>,
    override val ageRating: Int,
    override val movieLength: Int,
    override val year: Int,
    override val type: String,
    override val poster: PosterData,
    override val logo: LogoData
) : Movie

data class ResponseObjectData(override val name: String): ResponseObject