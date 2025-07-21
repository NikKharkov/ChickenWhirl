package com.chickiroad.game.game.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.game_background
import com.chickiroad.game.game.model.GameState
import com.chickiroad.game.game.presentation.components.CoinItem
import com.chickiroad.game.game.presentation.components.GameOverOverlay
import com.chickiroad.game.game.presentation.components.GameUI
import com.chickiroad.game.game.presentation.components.ObstacleItem
import com.chickiroad.game.game.presentation.components.PauseOverlay
import com.chickiroad.game.game.presentation.components.PlayerCharacter
import com.chickiroad.game.game.presentation.components.SwipeDetector
import com.chickiroad.game.platform.BackHandler
import com.chickiroad.game.ui.shared_components.Background
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(
    onHomeClick: () -> Unit,
    gameViewModel: GameViewModel = koinViewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    val density = LocalDensity.current

    BackHandler {
        gameViewModel.pauseGame()
    }

    SwipeDetector(
        onSwipeLeft = { gameViewModel.onSwipeLeft() },
        onSwipeRight = { gameViewModel.onSwipeRight() },
        onSwipeUp = { gameViewModel.onSwipeUp() }
    ) {
        Background(backgroundResImage = Res.drawable.game_background)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { size ->
                    with(density) {
                        gameViewModel.onScreenSizeChanged(
                            width = size.width.toDp().value,
                            height = size.height.toDp().value
                        )
                    }
                }
        ) {
            gameUiState.coins.forEach { coin ->
                CoinItem(
                    coin = coin,
                    modifier = Modifier
                )
            }

            gameUiState.obstacles.forEach { obstacle ->
                ObstacleItem(
                    obstacle = obstacle,
                    modifier = Modifier
                )
            }

            PlayerCharacter(
                player = gameUiState.player,
                gameState = gameUiState.gameState,
                modifier = Modifier
            )

            GameUI(
                score = gameUiState.score,
                health = gameUiState.health,
                onPause = { gameViewModel.pauseGame() }
            )
        }
    }

    when (gameUiState.gameState) {
        GameState.PAUSED -> {
            PauseOverlay(
                onResumeClick = { gameViewModel.resumeGame() },
                onRestartClick = { gameViewModel.restartGame() },
                onMainMenuClick = onHomeClick
            )
        }
        GameState.GAME_OVER -> {
            GameOverOverlay(
                collectedCoins = gameUiState.score,
                onRestartClick = { gameViewModel.restartGame() },
                onMainMenuClick = onHomeClick
            )
        }
        GameState.PLAYING -> {  }
    }
}
