package ru.sogya.avito.avito_test_task_trainee.home.data.entity

import ru.sogya.avito.avito_test_task_trainee.core.network.ResponseObject
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie

data class MovieData(
    override val id: Int = 0,
    override val name: String = "",
    override val alternativeName: String = "",
    override val rating: RatingData = RatingData(),
    override val description: String = "",
    override val genres: List<ResponseObject> = listOf(),
    override val countries: List<ResponseObject> = listOf(),
    override val ageRating: Int = 0,
    override val movieLength: Int = 0,
    override val year: Int = 0,
    override val type: String = "",
    override val poster: PosterData = PosterData(),
    override val logo: LogoData = LogoData()
) : Movie