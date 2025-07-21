package com.chickiroad.game.game.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.btn_continue
import chickenwhirl.composeapp.generated.resources.btn_main_menu
import chickenwhirl.composeapp.generated.resources.btn_restart
import com.chickiroad.game.ui.shared_components.CustomButton
import org.jetbrains.compose.resources.painterResource

@Composable
fun PauseOverlay(
    onResumeClick: () -> Unit,
    onRestartClick: () -> Unit,
    onMainMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomButton(
            image = painterResource(Res.drawable.btn_continue),
            onClick = onResumeClick,
            modifier = Modifier
                .width(216.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            image = painterResource(Res.drawable.btn_restart),
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