package ru.sogya.avito.avito_test_task_trainee.core.uikit.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Movie

@Composable
fun MovieExpandedItem(
    movie: Movie,
    index: Int
) {

    Row(modifier = Modifier.fillMaxSize().padding(vertical = 25.dp, horizontal = 15.dp)) {
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