package ru.sogya.avito.avito_test_task_trainee.film.data.entity

import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.FilmDomain

data class FilmData(
    override val id: Int,
    override val name: String,
    override val alternativeName: String,
    override val rating: Double,
    override val description: String,
    override val genres: List<String>,
    override val countries: List<String>,
    override val ageRating: Int,
    override val movieLength: Int,
    override val year: Int,
    override val type: String,
    override val poster: PosterData,
    override val logo: LogoData
) : FilmDomain