package com.chickiroad.game.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenwhirl.composeapp.generated.resources.Bungee_Regular
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.btn_back
import chickenwhirl.composeapp.generated.resources.btn_reset_progress
import chickenwhirl.composeapp.generated.resources.default_background
import chickenwhirl.composeapp.generated.resources.ic_music
import chickenwhirl.composeapp.generated.resources.ic_sound
import chickenwhirl.composeapp.generated.resources.settings_card
import chickenwhirl.composeapp.generated.resources.settings_header
import com.chickiroad.game.ui.screens.settings.components.SettingsItem
import com.chickiroad.game.ui.shared_components.Background
import com.chickiroad.game.ui.shared_components.CustomButton
import com.chickiroad.game.ui.theme.DarkRed
import com.chickiroad.game.ui.theme.Orange
import com.chickiroad.game.ui.theme.OrangeGradient
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    onHomeClick: () -> Unit,
    settingsViewModel: SettingsViewModel = koinViewModel()
) {
    val settingsState by settingsViewModel.state.collectAsState()

    Background(backgroundResImage = Res.drawable.default_background)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(340.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.settings_card),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(Res.drawable.settings_header),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(120.dp)
                    .offset(y = (-80).dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(brush = OrangeGradient, shape = RoundedCornerShape(16.dp))
                        .border(width = 1.dp, color = Orange, shape = RoundedCornerShape(16.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SettingsItem(
                        titleImage = painterResource(Res.drawable.ic_sound),
                        isEnabled = settingsState.isSoundEnabled,
                        onToggle = {
                            settingsViewModel.toggleSound()
                        }
                    )

                    SettingsItem(
                        titleImage = painterResource(Res.drawable.ic_music),
                        isEnabled = settingsState.isMusicEnabled,
                        onToggle = {
                            settingsViewModel.toggleMusic()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    image = painterResource(Res.drawable.btn_reset_progress),
                    onClick = {
                        settingsViewModel.playClickSound()
                        settingsViewModel.resetProgress()
                    },
                    modifier = Modifier
                        .width(232.dp)
                        .height(56.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "ARE YOU SURE?",
                    fontSize = 18.sp,
                    color = DarkRed,
                    fontFamily = FontFamily(Font(Res.font.Bungee_Regular))
                )
            }
        }

        CustomButton(
            image = painterResource(Res.drawable.btn_back),
            onClick = onHomeClick,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .width(84.dp)
                .height(40.dp)
        )
    }
}