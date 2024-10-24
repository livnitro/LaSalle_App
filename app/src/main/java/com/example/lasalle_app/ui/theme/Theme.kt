package com.example.lasalle_app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BlueDark,
    secondary = Red,
    onPrimary = White,
    onSecondary = White,
    background = GrayLight,
    onBackground = GrayDark
)

private val LightColorScheme = lightColorScheme(
    primary = BlueDark,
    secondary = Red,
    tertiary = BlueLight,
    onPrimary = White,
    onSecondary = White,
    background = GrayLight,
    onBackground = GrayDark
)

@Composable
fun LaSalle_AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = poppinsTypo,
        content = content
    )
}