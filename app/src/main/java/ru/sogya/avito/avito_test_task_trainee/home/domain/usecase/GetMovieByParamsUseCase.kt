package ru.sogya.avito.avito_test_task_trainee.home.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.sogya.avito.avito_test_task_trainee.home.data.MoviesPagingSource
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie

interface GetMovieByParamsUseCase : (String, String, String) -> Flow<PagingData<Movie>>
class GetMovieByParamsUseCaseImpl(private val homeRepository: HomeRepository) :
    GetMovieByParamsUseCase {

    private companion object {
        private const val INITIAL_PAGE_KEY = 1
        private const val PAGE_SIZE = 20
    }

    override fun invoke(
        ageRating: String,
        countries: String,
        year: String
    ): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        initialKey = INITIAL_PAGE_KEY,
        pagingSourceFactory = {
            MoviesPagingSource(
                homeRepository,
                year = if (year.isEmpty()) listOf() else listOf(year),
                ageRating = if (ageRating.isEmpty()) listOf() else listOf(ageRating),
                countries = if (countries.isEmpty()) listOf() else listOf(countries)
            )
        }
    ).flow.flowOn(Dispatchers.IO)
}