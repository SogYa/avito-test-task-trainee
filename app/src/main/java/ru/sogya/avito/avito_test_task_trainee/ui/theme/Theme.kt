package ru.sogya.avito.avito_test_task_trainee.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
internal fun AppTheme(
    textSize: TestAppSize = TestAppSize.Medium,
    paddingSize: TestAppSize = TestAppSize.Medium,
    corners: TestAppCorners = TestAppCorners.Rounded,
    content: @Composable () -> Unit
) {

    val colors = basePalette

    val typography = TestAppTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                TestAppSize.Small -> 18.sp
                TestAppSize.Medium -> 20.sp
                TestAppSize.Big -> 22.sp
            },
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default
        ),
        regular = TextStyle(
            fontSize = when (textSize) {
                TestAppSize.Small -> 12.sp
                TestAppSize.Medium -> 14.sp
                TestAppSize.Big -> 16.sp
            },
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default
        ),
        regularBold = TextStyle(
            fontSize = when (textSize) {
                TestAppSize.Small -> 12.sp
                TestAppSize.Medium -> 14.sp
                TestAppSize.Big -> 16.sp
            },
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        ),
        regularMedium = TextStyle(
            fontSize = when (textSize) {
                TestAppSize.Small -> 12.sp
                TestAppSize.Medium -> 14.sp
                TestAppSize.Big -> 16.sp
            },
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Default
        )
    )

    val shapes = TestAppShape(
        padding = when (paddingSize) {
            TestAppSize.Small -> 12.dp
            TestAppSize.Medium -> 16.dp
            TestAppSize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            TestAppCorners.Flat -> RoundedCornerShape(0.dp)
            TestAppCorners.Rounded -> RoundedCornerShape(16.dp)
        }
    )

    CompositionLocalProvider(
        LocalTestAppColors provides colors,
        LocalTestAppTypography provides typography,
        LocalTestAppShape provides shapes,
        content = content
    )
}