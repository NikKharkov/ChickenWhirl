package com.chickiroad.game.platform

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(onBack: () -> Unit)