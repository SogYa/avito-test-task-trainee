package ru.sogya.avito.avito_test_task_trainee.film.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.sogya.avito.avito_test_task_trainee.film.domain.MovieRepository
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review

class ReviewPagingSource(
    private val movieId: Int,
    private val movieRepository: MovieRepository
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return try {
            val currentPageNumber = params.key ?: 1
            val pageSize = 20
            val review = movieRepository.getReviewByMovieId(
                page = currentPageNumber,
                pageSize = pageSize,
                movieId
            )

            val nextKey =
                if (review.isEmpty()) null else review.size.plus(currentPageNumber).plus(1)

            val prevKey = if (currentPageNumber == 1) null else review.size.minus(pageSize)
            LoadResult.Page(
                prevKey = prevKey,
                nextKey = nextKey,
                data = review
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}