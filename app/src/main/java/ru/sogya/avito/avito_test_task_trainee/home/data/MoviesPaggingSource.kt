package ru.sogya.avito.avito_test_task_trainee.home.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Movie
import ru.sogya.avito.avito_test_task_trainee.home.domain.HomeRepository

class MoviesPaggingSource(
    private val homeRepository: HomeRepository
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
                null
            )

            val nextKey =
                if (movies.isEmpty()) null else movies.size.plus(currentPageNumber).plus(1)

            val prevKey = if (currentPageNumber == 1) null else movies.size.minus(pageSize)
            Log.d(
                "check",
                "page: $currentPageNumber, size ${movies.size}, nextKey $nextKey, previous $prevKey"
            )
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