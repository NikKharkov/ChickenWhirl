package com.chickiroad.game

import androidx.compose.ui.window.ComposeUIViewController
import com.chickiroad.game.di.initConfig

fun MainViewController() = ComposeUIViewController {
    initConfig()
    App()
}