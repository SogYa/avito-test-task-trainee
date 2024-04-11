package ru.sogya.avito.avito_test_task_trainee.search.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.PosterData
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.RatingData
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.toData
import ru.sogya.avito.avito_test_task_trainee.search.data.util.Constants
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

@Entity(tableName = Constants.SEARCH_TABLE)
data class SearchData(
    @PrimaryKey
    override val id: Int = 0,
    override val name: String = "",
    override val alternativeName: String = "",
    override val rating: RatingData = RatingData(),
    override val year: Int = 0,
    override val poster: PosterData = PosterData(),
) : Search

fun Search.toData(): SearchData = SearchData(
    id, name, alternativeName, rating.toData(), year, poster.toData()
)
