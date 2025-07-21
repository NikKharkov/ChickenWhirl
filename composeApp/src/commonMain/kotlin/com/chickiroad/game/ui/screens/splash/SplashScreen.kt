package com.chickiroad.game.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.app_logo
import chickenwhirl.composeapp.generated.resources.splash_background
import com.chickiroad.game.ui.shared_components.Background
import com.chickiroad.game.ui.shared_components.TextWithBorder
import com.chickiroad.game.ui.theme.GoldGradient
import com.chickiroad.game.ui.theme.WhiteGradient
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }

    Background(backgroundResImage = Res.drawable.splash_background)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.app_logo),
            contentDescription = null,
            modifier = Modifier.size(400.dp)
        )

        TextWithBorder(
            text = "LET’S FLY!",
            fontSize = 36.sp,
            textColor = WhiteGradient,
            borderColor = GoldGradient
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(onTimeout = {})
}