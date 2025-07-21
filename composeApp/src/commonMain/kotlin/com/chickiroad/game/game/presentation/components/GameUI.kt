package com.chickiroad.game.game.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.ic_pause
import chickenwhirl.composeapp.generated.resources.points_box
import com.chickiroad.game.ui.shared_components.CustomButton
import com.chickiroad.game.ui.shared_components.TextWithBorder
import com.chickiroad.game.ui.theme.GoldGradient
import com.chickiroad.game.ui.theme.WhiteGradient
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameUI(
    score: Int,
    health: Int,
    onPause: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.points_box),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            TextWithBorder(
                text = "$score",
                fontSize = 18.sp,
                borderColor = GoldGradient,
                textColor = WhiteGradient
            )
        }

        HealthBar(health = health)

        CustomButton(
            image = painterResource(Res.drawable.ic_pause),
            onClick = onPause,
            modifier = Modifier.size(44.dp)
        )
    }
}