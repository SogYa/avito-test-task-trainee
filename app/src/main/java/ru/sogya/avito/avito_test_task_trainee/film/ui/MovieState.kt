package ru.sogya.avito.avito_test_task_trainee.film.ui

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sogya.avito.avito_test_task_trainee.core.uikit.UDF
import ru.sogya.avito.avito_test_task_trainee.film.data.entity.FullMovieData
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.FullMovie
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Poster
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review


data class MovieViewState(
    val loading: Boolean = true,
    val movie: FullMovie = FullMovieData(),
    val posters: Flow<PagingData<Poster>> = MutableStateFlow(PagingData.empty()),
    val reviews: Flow<PagingData<Review>> = MutableStateFlow(PagingData.empty())
) : UDF.State

sealed interface MovieIntent : UDF.Intent {
    data object OnBackPressed : MovieIntent
    data class InitMovie(val movieId: Int) : MovieIntent
}

sealed interface MovieEffect : UDF.Effect {
    data object OnBackInvoked : MovieEffect
}