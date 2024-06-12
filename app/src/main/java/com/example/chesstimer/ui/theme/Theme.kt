package com.example.chesstimer.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
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

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object ChessTheme {
    val ctTypography: TmTypography
        @Composable get() = TmTypography()
    val fonts: Fonts = Fonts()
    val ctDimensions: CtDimensions
        @Composable get() = CtDimensions()
    val ctColors: CtColors
        @Composable get() = CtColors()
    val ctShapes: CtShapes
        @Composable get() = CtShapes()

}

data class TmTypography(
    val inputField: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = ChessTheme.fonts.robotoLight,
        fontWeight = FontWeight(300),
        color = Color(0xFF34303D),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    val timer: TextStyle = TextStyle(
        fontSize = 100.sp,
        lineHeight = 100.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.Black,
        letterSpacing = 5.sp
    ),
    val timerActivated: TextStyle = TextStyle(
        fontSize = 100.sp,
        lineHeight = 100.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.White,
        letterSpacing = 5.sp
    ),
    val timeBottomSheetTitle: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.Black
    ),
    val actionButton: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
    ),
    val timePill: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight(550),
        color = Color(0xFF2E2D40),
        fontFamily = ChessTheme.fonts.freigeistMedium
    ),
)

data class Fonts(
    val freigeistMedium: FontFamily = FontFamily(Font(R.font.freigeist_xconmedium)),
    val freigeistBold: FontFamily = FontFamily(Font(R.font.freigeist_xconbold)),
    val robotoMedium: FontFamily = FontFamily(Font(R.font.roboto_medium)),
    val robotoBold: FontFamily = FontFamily(Font(R.font.roboto_bold)),
    val robotoLight: FontFamily = FontFamily(Font(R.font.roboto_light)),
    val robotoRegular: FontFamily = FontFamily(Font(R.font.roboto_regular))
)

data class CtDimensions(
    val paddingXs: Dp = 4.dp,
    val paddingS: Dp = 8.dp,
    val paddingL: Dp = 12.dp,
    val padding: Dp = 16.dp,
    val paddingMedium: Dp = 20.dp,
    val paddingXL: Dp = 24.dp,
    val paddingXXL: Dp = 30.dp,
    val dialogPadding: Dp = 50.dp
)

data class CtColors(
    val timerActivated: Color = Color(0xFF388E3C),
    val actionButton: Color = Color(0xFF8BC34A),
)

data class CtShapes(
    val bottomSheet: Shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    val timePill: Shape = RoundedCornerShape(size = 1000.dp),
    val timePill2: Shape = RoundedCornerShape(size = 10.dp),
)
