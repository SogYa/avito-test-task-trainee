package ru.sogya.avito.avito_test_task_trainee.home.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie

class MoviesPagingSource(
    private val homeRepository: HomeRepository,
    private val ageRating: List<String>,
    private val countries: List<String>,
    private val year: List<String>
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPageNumber = params.key ?: 1
            val pageSize = 20
            val movies = homeRepository.getMoviesByParams(
                page = currentPageNumber,
                pageSize = pageSize,
                ageRating = ageRating.ifEmpty { null },
                countries = countries.ifEmpty { null },
                year = year.ifEmpty { null }
            )

            val nextKey =
                if (movies.isEmpty()) null else movies.size.plus(currentPageNumber).plus(1)

            val prevKey = if (currentPageNumber == 1) null else movies.size.minus(pageSize)
            LoadResult.Page(
                prevKey = prevKey,
                nextKey = nextKey,
                data = movies
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}