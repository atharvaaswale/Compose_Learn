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

val ColorScheme.UIColor
    @Composable
    get() = if (isSystemInDarkTheme()) UIColorDark else UIColorLight

val UIColorDark = Color(0xFF181924)
val UIColorLight = Color(0xFFEEF0F3)
val BlueHeading = Color(0xFF185AC4)
val DimLine = Color(0xFF929292)
