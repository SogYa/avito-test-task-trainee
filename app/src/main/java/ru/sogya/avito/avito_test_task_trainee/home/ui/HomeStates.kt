package ru.sogya.avito.avito_test_task_trainee.home.ui

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sogya.avito.avito_test_task_trainee.core.uikit.UDF
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Movie

data class HomeViewState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val currentPage: Int = 0,
    val searchActive: Boolean = false,
    val movies: Flow<PagingData<Movie>> = MutableStateFlow(PagingData.empty()),
) : UDF.State

sealed interface HomeIntent : UDF.Intent {
    data class SetSearchActive(val active: Boolean) : HomeIntent
    data class SearchByName(val name: String) : HomeIntent
    data class FilterByYear(val year: List<String>) : HomeIntent
    data class FilterByCountries(val countries: List<String>) : HomeIntent
    data class FilterByAgeRating(val ageRating: List<String>) : HomeIntent
    data class OpenMovie(val movieId: Int) : HomeIntent
}

sealed interface HomeEffect : UDF.Effect {
    data class NavigateToMovieScreen(val movieId: Int) : HomeEffect
}