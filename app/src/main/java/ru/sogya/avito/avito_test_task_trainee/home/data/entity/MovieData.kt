package ru.sogya.avito.avito_test_task_trainee.home.data.entity

import ru.sogya.avito.avito_test_task_trainee.core.network.ResponseObject
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie

data class MovieData(
    override val id: Int,
    override val name: String,
    override val alternativeName: String,
    override val rating: RatingData,
    override val description: String,
    override val genres: List<ResponseObject>,
    override val countries: List<ResponseObject>,
    override val ageRating: Int,
    override val movieLength: Int,
    override val year: Int,
    override val type: String,
    override val poster: PosterData,
    override val logo: LogoData
) : Movie