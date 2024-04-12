package ru.sogya.avito.avito_test_task_trainee.core.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBox(
    rating: Double
) {
    val color = when {
        rating > 7 -> Color.Green
        rating < 4 -> Color.Red
        else -> Color.Yellow
    }
    Box(
        modifier = Modifier.background(color = color).padding(all = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 2.dp),
            text = rating.toString()
        )
    }
}

@Composable
fun RatingAndYearRow(
    year: Int,
    rating: Double
) {
    val color = when {
        rating > 7 -> Color.Green
        rating < 4 -> Color.Red
        else -> Color.Gray
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(horizontal = 2.dp),
            text = rating.toString(),
            color = color
        )
        Text(
            modifier = Modifier.padding(horizontal = 2.dp),
            text = year.toString(),
        )
    }
}