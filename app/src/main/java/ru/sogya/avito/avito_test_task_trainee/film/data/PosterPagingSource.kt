package ru.sogya.avito.avito_test_task_trainee.film.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Poster

class PosterPagingSource(
    private val movieId: Int,
    private val movieRepository: MovieRepository
) : PagingSource<Int, Poster>() {

    override fun getRefreshKey(state: PagingState<Int, Poster>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Poster> {
        return try {
            val currentPageNumber = params.key ?: 1
            val pageSize = 20
            val poster = movieRepository.getPostersByMovieId(
                page = currentPageNumber,
                pageSize = pageSize,
                movieId
            )

            val nextKey =
                if (poster.isEmpty()) null else poster.size.plus(currentPageNumber).plus(1)

            val prevKey = if (currentPageNumber == 1) null else poster.size.minus(pageSize)
            LoadResult.Page(
                prevKey = prevKey,
                nextKey = nextKey,
                data = poster
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}