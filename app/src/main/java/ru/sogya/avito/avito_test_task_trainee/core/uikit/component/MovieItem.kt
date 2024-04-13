package ru.sogya.avito.avito_test_task_trainee.core.uikit.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.sogya.avito.avito_test_task_trainee.home.domain.entity.Movie
import ru.sogya.avito.avito_test_task_trainee.search.domain.entity.Search

@Composable
fun MovieExpandedItem(
    movie: Movie,
    index: Int,
    onClick: (movieId: Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize().padding(vertical = 25.dp, horizontal = 15.dp)
            .clickable { onClick(movie.id) }) {
        Box(
            modifier = Modifier.fillMaxSize()
                .weight(weight = 1f),
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.poster.url)
                    .build(),
//            placeholder = painterResource(MR.images.employee_placheholer.drawableResId),
                filterQuality = FilterQuality.Medium,
                contentDescription = null
            )
            RatingBox(movie.rating.imdb)
        }
        Column(modifier = Modifier.fillMaxWidth().weight(weight = 2f).padding(horizontal = 15.dp)) {
            Text(
                text = "${index + 1}. ${movie.name}"
            )
            Text(
                text = "${movie.alternativeName}, ${movie.year}"
            )
            Text(
                text = "${movie.countries[0].name} · ${movie.genres[0].name}"
            )
        }
    }
}

@Composable
fun MovieSmallItem(
    search: Search,
    onClick: (search: Search) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp)
            .padding(vertical = 10.dp, horizontal = 15.dp).clickable { onClick(search) },
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize().weight(1f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(search.poster.url)
                .build(),
//            placeholder = painterResource(MR.images.employee_placheholer.drawableResId),
            filterQuality = FilterQuality.Medium,
            contentDescription = null
        )
        Column(modifier = Modifier.fillMaxWidth().weight(weight = 2f).padding(horizontal = 15.dp)) {
            Text(
                text = search.name
            )
            RatingAndYearRow(rating = search.rating.imdb, year = search.year)
        }
    }
}