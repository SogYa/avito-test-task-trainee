package ru.sogya.avito.avito_test_task_trainee.film.data.entity

import ru.sogya.avito.avito_test_task_trainee.core.network.ResponseObject
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.FullMovie
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.RatingData

data class FullMovieData(
    override val id: Int = 0,
    override val type: String = "",
    override val name: String = "",
    override val description: String = "",
    override val rating: RatingData = RatingData(),
    override val genres: List<ResponseObject> = listOf(),
    override val countries: List<ResponseObject> = listOf(),
    override val persons: List<PersonData> = listOf(),
    override val poster: PosterData = PosterData(),
    override val backdrop: PosterData = PosterData()
) : FullMovie