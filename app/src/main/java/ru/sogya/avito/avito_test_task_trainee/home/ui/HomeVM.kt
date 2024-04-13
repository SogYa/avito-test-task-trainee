package ru.sogya.avito.avito_test_task_trainee.home.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.sogya.avito.avito_test_task_trainee.core.uikit.BaseViewModel
import ru.sogya.avito.avito_test_task_trainee.home.domain.usecase.GetMovieByParamsUseCase
import ru.sogya.avito.avito_test_task_trainee.search.data.util.Constants
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.GetAllSearchesHistoryUseCase
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.InsertSearchUseCase
import ru.sogya.avito.avito_test_task_trainee.search.domain.usecase.SearchByNameUseCase
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getMovieByParamsUseCase: GetMovieByParamsUseCase,
    private val getAllSearchesHistoryUseCase: GetAllSearchesHistoryUseCase,
    private val searchByNameUseCase: SearchByNameUseCase,
    private val insertSearchUseCase: InsertSearchUseCase,
) : BaseViewModel<HomeViewState, HomeIntent, HomeEffect>(HomeViewState()) {
    private var searchHistory: List<Search> = listOf()
    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            getAllSearchesHistoryUseCase.invoke().collect { searches ->
                searchHistory = searches
                setState {
                    copy(searches = searchHistory)
                }
            }
        }
    }

    override fun handleIntents(intent: HomeIntent) {
        viewModelScope.launch {
            when (intent) {
                is HomeIntent.OpenMovie -> setEffect {
                    HomeEffect.NavigateToMovieScreen(intent.movieId)
                }

                is HomeIntent.OpenFilterBottomSheet -> setState {
                    copy(isFilterBottomSheetOpen = true)
                }

                is HomeIntent.CloseFilterBottomSheet -> setState {
                    copy(isFilterBottomSheetOpen = false)
                }

                is HomeIntent.FilterByAgeRating -> setState {
                    copy(ageRatingFilter = intent.ageRating, setFilterButtonEnabled = true)
                }

                is HomeIntent.FilterByCountries -> setState {
                    copy(countiresFilter = intent.countries, setFilterButtonEnabled = true)
                }

                is HomeIntent.FilterByYear -> setState {
                    copy(yearFilter = intent.year, setFilterButtonEnabled = true)
                }

                is HomeIntent.FilterByParams -> {
                    getMovieByParams(
                        ageRating = intent.ageRating,
                        counties = intent.countries,
                        year = intent.year
                    )
                    setState {
                        copy(setFilterButtonEnabled = false)
                    }
                }

                is HomeIntent.SearchByName -> {
                    setState {
                        copy(searchQuery = intent.name)
                    }
                    if (intent.name.isNotBlank()) {
                        searchJob?.cancel()
                        searchJob = viewModelScope.launch {
                            delay(Constants.SEARCH_DELAY)
                            searchByNameUseCase.invoke(intent.name).collect { searches ->
                                setState {
                                    copy(searches = searches)
                                }

                            }
                        }
                    } else {
                        searchJob?.cancel()
                        setState {
                            copy(searches = searchHistory)
                        }
                    }
                }

                is HomeIntent.SetSearchActive -> setState {
                    copy(searchActive = intent.active)
                }

                is HomeIntent.OpenMovieFromSearch -> {
                    insertSearchUseCase.invoke(intent.search)
                    setEffect {
                        HomeEffect.NavigateToMovieScreen(intent.search.id)
                    }
                }
            }
        }
    }

    private suspend fun getMovieByParams(
        ageRating: String,
        counties: String,
        year: String
    ) {
        setState {
            copy(
                movies = getMovieByParamsUseCase(ageRating, counties, year),
                isLoading = false
            )
        }
    }
}