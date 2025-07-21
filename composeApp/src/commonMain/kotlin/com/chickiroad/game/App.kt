package com.chickiroad.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chickiroad.game.game.presentation.GameScreen
import com.chickiroad.game.shop.presentation.ShopScreen
import com.chickiroad.game.ui.navigation.Screen
import com.chickiroad.game.ui.screens.menu.MenuScreen
import com.chickiroad.game.ui.screens.settings.SettingsScreen
import com.chickiroad.game.ui.screens.settings.SettingsViewModel
import com.chickiroad.game.ui.screens.splash.SplashScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val navController = rememberNavController()
    val settingsViewModel: SettingsViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.MenuScreen.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.MenuScreen.route) {
            MenuScreen(
                onPlayClick = {
                    settingsViewModel.playClickSound()
                    navController.navigate(Screen.GameScreen.route) {
                        popUpTo(Screen.GameScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onShopClick = {
                    settingsViewModel.playClickSound()
                    navController.navigate(Screen.ShopScreen.route)
                },
                onSettingsClick = {
                    settingsViewModel.playClickSound()
                    navController.navigate(Screen.SettingsScreen.route)
                }
            )
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(onHomeClick = {
                settingsViewModel.playClickSound()
                navController.navigate(Screen.MenuScreen.route)
            })
        }

        composable(Screen.GameScreen.route) {
            GameScreen(onHomeClick = {
                settingsViewModel.playClickSound()
                navController.navigate(Screen.MenuScreen.route) {
                    popUpTo(0)
                }
            })
        }

        composable(Screen.ShopScreen.route) {
            ShopScreen(
                onHomeClick = {
                    settingsViewModel.playClickSound()
                    navController.navigate(Screen.MenuScreen.route)
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        settingsViewModel.startBackgroundMusic()
    }
}

