package ru.sogya.avito.avito_test_task_trainee.film.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.VSpacer
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review

@Composable
fun ReviewItem(
    review: Review
) = Column(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)) {
    Text(text = review.title)
    VSpacer(size = 2.dp)
    Text(text = review.type)
    VSpacer(size = 1.dp)
    Text(text = review.author)
    VSpacer(size = 5.dp)
    Text(text = review.review)
}