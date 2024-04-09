package ru.sogya.avito.avito_test_task_trainee.core.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sogya.avito.avito_test_task_trainee.core.navigation.NavGraph
import ru.sogya.avito.avito_test_task_trainee.ui.theme.AvitotesttasktraineeTheme

@Composable
fun TestApp() {
    AvitotesttasktraineeTheme {
        CompositionLocalProvider {
            Box(
                contentAlignment = Alignment.TopCenter,
            ) {
                Column(
                    modifier = Modifier
                        .width(600.dp)
                ) {
                    NavGraph()
                }
            }
        }
    }
}