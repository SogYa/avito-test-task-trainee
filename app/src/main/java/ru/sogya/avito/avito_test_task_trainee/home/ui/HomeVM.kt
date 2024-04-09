package ru.sogya.avito.avito_test_task_trainee.home.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sogya.avito.avito_test_task_trainee.core.uikit.BaseViewModel
import ru.sogya.avito.avito_test_task_trainee.home.data.api.request.MovieRequestData
import ru.sogya.avito.avito_test_task_trainee.home.domain.usecase.GetMovieByParamsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getMovieByParamsUseCase: GetMovieByParamsUseCase
) : BaseViewModel<HomeViewState, HomeIntent, HomeEffect>(HomeViewState()) {

    init {
        viewModelScope.launch {
            setState {
                copy(movies = getMovieByParamsUseCase.invoke(1, 1, MovieRequestData()))
            }
        }
    }

    override fun handleIntents(intent: HomeIntent) {
        viewModelScope.launch {


            when (intent) {
                is HomeIntent.OpenMovie -> TODO()
                is HomeIntent.FilterByAgeRating -> TODO()
                is HomeIntent.FilterByCountries -> TODO()
                is HomeIntent.FilterByYear -> TODO()
                is HomeIntent.SearchByName -> TODO()
                is HomeIntent.SetSearchActive -> setState {
                    copy(searchActive = intent.active)
                }
            }
        }
    }
}