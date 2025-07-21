package com.chickiroad.game.game.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.btn_main_menu
import chickenwhirl.composeapp.generated.resources.btn_play_again
import chickenwhirl.composeapp.generated.resources.coin
import chickenwhirl.composeapp.generated.resources.game_over_background
import chickenwhirl.composeapp.generated.resources.game_over_header
import chickenwhirl.composeapp.generated.resources.settings_card
import com.chickiroad.game.ui.shared_components.Background
import com.chickiroad.game.ui.shared_components.CustomButton
import com.chickiroad.game.ui.shared_components.TextWithBorder
import com.chickiroad.game.ui.theme.DarkRed
import com.chickiroad.game.ui.theme.GoldGradient
import com.chickiroad.game.ui.theme.OrangeGradient
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameOverOverlay(
    collectedCoins: Int,
    onRestartClick: () -> Unit,
    onMainMenuClick: () -> Unit
) {
    Background(backgroundResImage = Res.drawable.game_over_background)

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(260.dp)
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.settings_card),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(Res.drawable.game_over_header),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(120.dp)
                    .offset(y = (-80).dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.coin),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )

                    TextWithBorder(
                        text = "Coins:",
                        fontSize = 20.sp,
                        textColor = SolidColor(DarkRed),
                        borderColor = OrangeGradient
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                TextWithBorder(
                    text = "$collectedCoins",
                    fontSize = 20.sp,
                    textColor = GoldGradient,
                    borderColor = OrangeGradient
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            image = painterResource(Res.drawable.btn_play_again),
            onClick = onRestartClick,
            modifier = Modifier
                .width(216.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            image = painterResource(Res.drawable.btn_main_menu),
            onClick = onMainMenuClick,
            modifier = Modifier
                .width(216.dp)
                .height(60.dp)
        )
    }
}