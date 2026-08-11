package com.unlymng.optimization.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Цвета приложения
val NeonCyan = Color(0xFF00E5FF)
val NeonPurple = Color(0xFFB026FF)
val DarkBackground = Color(0xFF000000)
val SurfaceDark = Color(0xFF1A1A1A)
val ErrorRed = Color(0xFFFF4444)
val SuccessGreen = Color(0xFF00CC00)

val DarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    secondary = NeonPurple,
    tertiary = SuccessGreen,
    background = DarkBackground,
    surface = SurfaceDark,
    error = ErrorRed,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun UnlyOptimizationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
