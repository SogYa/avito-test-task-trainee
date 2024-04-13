package ru.sogya.avito.avito_test_task_trainee.film.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sogya.avito.avito_test_task_trainee.core.uikit.BaseViewModel
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetMovieByIdUseCase
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetPostersByMovieIdUseCase
import ru.sogya.avito.avito_test_task_trainee.film.domain.usecase.GetReviewByMovieIdUseCase
import javax.inject.Inject

@HiltViewModel
class MovieVM @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getPostersByMovieIdUseCase: GetPostersByMovieIdUseCase,
    private val getReviewByMovieIdUseCase: GetReviewByMovieIdUseCase
) : BaseViewModel<MovieViewState, MovieIntent, MovieEffect>(MovieViewState()) {

    override fun handleIntents(intent: MovieIntent) {
        viewModelScope.launch {
            when (intent) {
                MovieIntent.OnBackPressed -> setEffect {
                    MovieEffect.OnBackInvoked
                }

                is MovieIntent.InitMovie -> initMovie(intent.movieId)
            }
        }
    }

    private suspend fun initMovie(movieId: Int) {
        val reviewsFlow = getReviewByMovieIdUseCase.invoke(movieId)
        val postersFlow = getPostersByMovieIdUseCase.invoke(movieId)
        getMovieByIdUseCase.invoke(movieId).collect { movie ->
            setState {
                copy(movie = movie, reviews = reviewsFlow, posters = postersFlow, loading = false)
            }
        }
    }
}