package ru.sogya.avito.avito_test_task_trainee.film.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.FullMovie

interface GetMovieByIdUseCase : suspend (Int) -> Flow<FullMovie>

class GetMovieByIdUseCaseImpl(private val movieRepository: MovieRepository) : GetMovieByIdUseCase {
    override suspend fun invoke(movieId: Int): Flow<FullMovie> =
        movieRepository.getMovieById(movieId)
}