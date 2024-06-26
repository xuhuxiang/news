package com.example.anew.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)

//@Composable
//fun NewTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}
private val LightColorPalette = NewColors(
    bottomBar = white1,
    background = white2,
    listItem = white,
    divider = white3,
    chatPage = white2,
    textPrimary = black3,
    textPrimaryMe = black3,
    textSecondary = grey1,
    onBackground = grey3,
    icon = black,
    iconCurrent = green3,
    badge = red1,
    onBadge = white,
    bubbleMe = green1,
    bubbleOthers = white,
    textFieldBackground = white,
    more = grey4,
    chatPageBgAlpha = 0f,
)
private val DarkColorPalette = NewColors(
    bottomBar = black1,
    background = black2,
    listItem = black3,
    divider = black4,
    chatPage = black2,
    textPrimary = white4,
    textPrimaryMe = black6,
    textSecondary = grey1,
    onBackground = grey1,
    icon = white5,
    iconCurrent = green3,
    badge = red1,
    onBadge = white,
    bubbleMe = green2,
    bubbleOthers = black5,
    textFieldBackground = black7,
    more = grey5,
    chatPageBgAlpha = 0f,
)
private val NewYearColorPalette = NewColors(
    bottomBar = red4,
    background = red5,
    listItem = red2,
    divider = red3,
    chatPage = red5,
    textPrimary = white,
    textPrimaryMe = black6,
    textSecondary = grey2,
    onBackground = grey2,
    icon = white5,
    iconCurrent = green3,
    badge = yellow1,
    onBadge = black3,
    bubbleMe = green2,
    bubbleOthers = red6,
    textFieldBackground = red7,
    more = red8,
    chatPageBgAlpha = 1f,
)

private val LocalNewColors = compositionLocalOf {
    LightColorPalette
}

object NewTheme {
    val colors: NewColors
        @Composable
        get() = LocalNewColors.current
    enum class Theme {
        Light, Dark, NewYear
    }
}

@Stable
class NewColors(
    bottomBar: Color,
    background: Color,
    listItem: Color,
    divider: Color,
    chatPage: Color,
    textPrimary: Color,
    textPrimaryMe: Color,
    textSecondary: Color,
    onBackground: Color,
    icon: Color,
    iconCurrent: Color,
    badge: Color,
    onBadge: Color,
    bubbleMe: Color,
    bubbleOthers: Color,
    textFieldBackground: Color,
    more: Color,
    chatPageBgAlpha: Float,
) {
    var bottomBar: Color by mutableStateOf(bottomBar)
        private set
    var background: Color by mutableStateOf(background)
        private set
    var listItem: Color by mutableStateOf(listItem)
        private set
    var chatListDivider: Color by mutableStateOf(divider)
        private set
    var chatPage: Color by mutableStateOf(chatPage)
        private set
    var textPrimary: Color by mutableStateOf(textPrimary)
        private set
    var textPrimaryMe: Color by mutableStateOf(textPrimaryMe)
        private set
    var textSecondary: Color by mutableStateOf(textSecondary)
        private set
    var onBackground: Color by mutableStateOf(onBackground)
        private set
    var icon: Color by mutableStateOf(icon)
        private set
    var iconCurrent: Color by mutableStateOf(iconCurrent)
        private set
    var badge: Color by mutableStateOf(badge)
        private set
    var onBadge: Color by mutableStateOf(onBadge)
        private set
    var bubbleMe: Color by mutableStateOf(bubbleMe)
        private set
    var bubbleOthers: Color by mutableStateOf(bubbleOthers)
        private set
    var textFieldBackground: Color by mutableStateOf(textFieldBackground)
        private set
    var more: Color by mutableStateOf(more)
        private set
    var chatPageBgAlpha: Float by mutableStateOf(chatPageBgAlpha)
        private set
}

@Composable
fun NewTheme(theme: NewTheme.Theme = NewTheme.Theme.Light, content: @Composable() () -> Unit) {
    val targetColors = when (theme) {
        NewTheme.Theme.Light -> LightColorPalette
        NewTheme.Theme.Dark -> DarkColorPalette
        NewTheme.Theme.NewYear -> NewYearColorPalette
    }

//    val bottomBar = animateColorAsState(targetColors.bottomBar, TweenSpec(600))
//    val background = animateColorAsState(targetColors.background, TweenSpec(600))
//    val listItem = animateColorAsState(targetColors.listItem, TweenSpec(600))
//    val chatListDivider = animateColorAsState(targetColors.chatListDivider, TweenSpec(600))
//    val chatPage = animateColorAsState(targetColors.chatPage, TweenSpec(600))
//    val textPrimary = animateColorAsState(targetColors.textPrimary, TweenSpec(600))
//    val textPrimaryMe = animateColorAsState(targetColors.textPrimaryMe, TweenSpec(600))
//    val textSecondary = animateColorAsState(targetColors.textSecondary, TweenSpec(600))
//    val onBackground = animateColorAsState(targetColors.onBackground, TweenSpec(600))
//    val icon = animateColorAsState(targetColors.icon, TweenSpec(600))
//    val iconCurrent = animateColorAsState(targetColors.iconCurrent, TweenSpec(600))
//    val badge = animateColorAsState(targetColors.badge, TweenSpec(600))
//    val onBadge = animateColorAsState(targetColors.onBadge, TweenSpec(600))
//    val bubbleMe = animateColorAsState(targetColors.bubbleMe, TweenSpec(600))
//    val bubbleOthers = animateColorAsState(targetColors.bubbleOthers, TweenSpec(600))
//    val textFieldBackground = animateColorAsState(targetColors.textFieldBackground, TweenSpec(600))
//    val more = animateColorAsState(targetColors.more, TweenSpec(600))
//    val chatPageBgAlpha = animateFloatAsState(targetColors.chatPageBgAlpha, TweenSpec(600))

//    val colors = NewColors(
//        bottomBar = bottomBar.value,
//        background = background.value,
//        listItem = listItem.value,
//        divider = chatListDivider.value,
//        chatPage = chatPage.value,
//        textPrimary = textPrimary.value,
//        textPrimaryMe = textPrimaryMe.value,
//        textSecondary = textSecondary.value,
//        onBackground = onBackground.value,
//        icon = icon.value,
//        iconCurrent = iconCurrent.value,
//        badge = badge.value,
//        onBadge = onBadge.value,
//        bubbleMe = bubbleMe.value,
//        bubbleOthers = bubbleOthers.value,
//        textFieldBackground = textFieldBackground.value,
//        more = more.value,
//        chatPageBgAlpha = chatPageBgAlpha.value,
//    )

    CompositionLocalProvider(LocalNewColors provides targetColors) {
        androidx.compose.material.MaterialTheme(
            shapes = shapes,
            content = content
        )
    }
}