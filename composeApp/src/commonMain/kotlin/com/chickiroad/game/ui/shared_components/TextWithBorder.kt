package com.chickiroad.game.ui.shared_components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import chickenwhirl.composeapp.generated.resources.Bungee_Regular
import chickenwhirl.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun TextWithBorder(
    text: String,
    fontSize: TextUnit,
    textColor: Brush,
    borderColor: Brush
) {
    Box {
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize,
                fontFamily = FontFamily(Font(Res.font.Bungee_Regular)),
                brush = borderColor,
                textAlign = TextAlign.Center,
                drawStyle = Stroke(
                    width = 12f,
                    join = StrokeJoin.Round
                )
            )
        )
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize,
                fontFamily = FontFamily(Font(Res.font.Bungee_Regular)),
                brush = textColor,
                textAlign = TextAlign.Center
            )
        )
    }
}