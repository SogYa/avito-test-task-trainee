package ru.sogya.avito.avito_test_task_trainee.film.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Person

@Composable
fun PersonItem(
    person: Person
) = Row(modifier = Modifier.padding(5.dp)) {
    AsyncImage(
        modifier = Modifier.size(width = 40.dp, height = 48.dp).clip(CircleShape),
        model = ImageRequest.Builder(LocalContext.current)
            .data(person.photo)
            .build(),
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.High,
        contentDescription = null
    )
    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
        Text(
            text = person.name
        )
        if (!person.description.isNullOrEmpty())
            Text(
                modifier = Modifier.padding(vertical = 2.dp),
                text = person.description!!
            )
        Text(
            text = person.profession
        )
    }
}