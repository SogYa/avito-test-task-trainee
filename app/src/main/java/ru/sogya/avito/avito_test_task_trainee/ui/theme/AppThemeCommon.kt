package ru.sogya.avito.avito_test_task_trainee.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


data class TestAppColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val accent: Color,
    val white: Color,
    val disable: Color,
    val secondaryText: Color,
    val accentDisabled: Color,
    val unselectedWhite: Color
)

data class TestAppTypography(
    val heading: TextStyle,
    val regular: TextStyle,
    val regularBold: TextStyle,
    val regularMedium: TextStyle
)

data class TestAppShape(
    val padding: Dp,
    val cornersStyle: Shape
)

object TestAppTheme {
    internal val colors: TestAppColors
        @ReadOnlyComposable @Composable get() = LocalTestAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    internal val shape: TestAppShape
        @ReadOnlyComposable @Composable get() = LocalTestAppShape.current
}

enum class TestAppSize {
    Small, Medium, Big
}

enum class TestAppCorners {
    Flat, Rounded
}


internal val LocalTestAppColors = staticCompositionLocalOf<TestAppColors> {
    error("No colors provided")
}

internal val LocalTestAppTypography = staticCompositionLocalOf<TestAppTypography> {
    error("No fonts provided")
}

internal val LocalTestAppShape = staticCompositionLocalOf<TestAppShape> {
    error("No shapes provided")
}