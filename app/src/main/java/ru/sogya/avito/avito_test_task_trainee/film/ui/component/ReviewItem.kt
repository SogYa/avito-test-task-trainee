package ru.sogya.avito.avito_test_task_trainee.film.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.VSpacer
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Review
import ru.sogya.avito.avito_test_task_trainee.ui.theme.TestAppTheme

@Composable
fun ReviewItem(
    review: Review
) = Column(modifier = Modifier.padding(horizontal = 15.dp).padding(bottom = 20.dp)) {
    Text(
        text = review.title,
        style = TestAppTheme.typography.h4
    )
    VSpacer(size = 1.dp)
    Text(
        text = review.author,
        style = TestAppTheme.typography.p2Normal
    )

    VSpacer(size = 1.dp)
    Text(
        text = review.type,
        style = TestAppTheme.typography.p4Light
    )
    VSpacer(size = 5.dp)
    Text(
        text = review.review,
        style = TestAppTheme.typography.p2Normal
    )
}