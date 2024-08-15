package com.unreal.composelearn.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFBCDAFF)
val PurpleGrey80 = Color(0xFFC2CCDC)
val Pink80 = Color(0xFFB8D9EF)

val Purple40 = Color(0xFF506CA4)
val PurpleGrey40 = Color(0xFF5B6171)
val Pink40 = Color(0xFF7D5260)

val UIColor
    @Composable
    get() = if (isSystemInDarkTheme()) UIColorDark else UIColorLight

val UIColorDark = Color(0xFF181924)
val UIColorLight = Color(0xFFEEF0F3)
val BlueHeading = Color(0xFF185AC4)
val DimLine = Color(0xFF929292)
val dimFontDarkTheme = Color(0xFFD5D5D5)
val dimFontLightTheme = Color(0xFF353535)
val textNormalDarkTheme = Color(0xFFD5D5D5)
val textNormalLightTheme = Color(0xFF000000)
val carColorDarkTheme = Color(0xFF1D2036)
val carColorLightTheme = Color(0xFFD2D7E0)
val textDim
    @Composable
    get() = if (isSystemInDarkTheme()) dimFontDarkTheme else dimFontLightTheme
val textNormal
    @Composable
    get() = if (isSystemInDarkTheme()) textNormalDarkTheme else textNormalLightTheme
val cardBG
    @Composable
    get() = if (isSystemInDarkTheme()) carColorDarkTheme else carColorLightTheme
