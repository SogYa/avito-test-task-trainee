package ru.sogya.avito.avito_test_task_trainee.home.ui

import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sogya.avito.avito_test_task_trainee.core.uikit.UDF
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

data class HomeViewState(
    val isLoading: Boolean = true,
    val searchQuery: String = "",
    val searchActive: Boolean = false,
    val isFilterBottomSheetOpen: Boolean = false,
    val searches: List<Search> = listOf(),
    val movies: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty()),
    val ageRatingFilter: String = "",
    val countiresFilter: String = "",
    val yearFilter: String = "",
    val setFilterButtonEnabled: Boolean = false
) : UDF.State

sealed interface HomeIntent : UDF.Intent {
    data class SetSearchActive(val active: Boolean) : HomeIntent
    data class SearchByName(val name: String) : HomeIntent
    data object OpenFilterBottomSheet : HomeIntent
    data object CloseFilterBottomSheet : HomeIntent
    data class FilterByAgeRating(val ageRating: String) : HomeIntent
    data class FilterByYear(val year: String) : HomeIntent
    data class FilterByCountries(val countries: String) : HomeIntent

    data class FilterByParams(
        val ageRating: String = "",
        val year: String = "",
        val countries: String = ""
    ) : HomeIntent

    data class OpenMovie(val movieId: Int) : HomeIntent
    data class OpenMovieFromSearch(val search: Search) : HomeIntent
}

sealed interface HomeEffect : UDF.Effect {
    data class NavigateToMovieScreen(val movieId: Int) : HomeEffect
}