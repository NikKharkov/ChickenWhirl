package com.chickiroad.game.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val LightGreen = Color(0xFF56EC58)
val Gray = Color(0xFFB3B3B3)
val Beige = Color(0xFFFFD49E)
val Orange = Color(0xFFFF9C37)
val DarkRed = Color(0xFF6C2E00)

val WhiteGradient = Brush.linearGradient(
    colors = listOf(
        Color.White,
        Color(0xFFFFB672)
    )
)

val GoldGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFF723400),
        Color(0xFFFF9C00)
    )
)

val OrangeGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFFFFB76B),
        Color(0xFFFF992E)
    )
)