package com.example.chesstimer.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.chesstimer.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun ChessTimerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object ChessTheme {
    val typography: CtTypography
        @Composable get() = CtTypography()
    val fonts: Fonts = Fonts()
    val dimensions: Dimensions
        @Composable get() = Dimensions()
    val colors: CtColors
        @Composable get() = CtColors()
    val shapes: Shapes
        @Composable get() = Shapes()

}

data class Fonts(
    val freigeistMedium: FontFamily = FontFamily(Font(R.font.freigeist_xconmedium)),
    val freigeistBold: FontFamily = FontFamily(Font(R.font.freigeist_xconbold)),
    val robotoMedium: FontFamily = FontFamily(Font(R.font.roboto_medium)),
    val robotoBold: FontFamily = FontFamily(Font(R.font.roboto_bold)),
    val robotoLight: FontFamily = FontFamily(Font(R.font.roboto_light)),
    val robotoRegular: FontFamily = FontFamily(Font(R.font.roboto_regular))
)
