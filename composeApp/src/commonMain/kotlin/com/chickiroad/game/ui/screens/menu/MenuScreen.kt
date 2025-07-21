package com.chickiroad.game.ui.screens.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.btn_play
import chickenwhirl.composeapp.generated.resources.btn_settings
import chickenwhirl.composeapp.generated.resources.btn_shop
import chickenwhirl.composeapp.generated.resources.default_background
import com.chickiroad.game.ui.shared_components.Background
import com.chickiroad.game.ui.shared_components.CustomButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MenuScreen(
    onPlayClick: () -> Unit,
    onShopClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Background(backgroundResImage = Res.drawable.default_background)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomButton(
            image = painterResource(Res.drawable.btn_play),
            onClick = onPlayClick,
            modifier = Modifier
                .width(216.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            image = painterResource(Res.drawable.btn_shop),
            onClick = onShopClick,
            modifier = Modifier
                .width(216.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            image = painterResource(Res.drawable.btn_settings),
            onClick = onSettingsClick,
            modifier = Modifier
                .width(216.dp)
                .height(60.dp)
        )
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(
        onPlayClick = {},
        onShopClick = {},
        onSettingsClick = {}
    )
}