package com.chickiroad.game.game.presentation

import com.chickiroad.game.managers.SettingsManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chickiroad.game.game.model.Coin
import com.chickiroad.game.game.model.GameBonuses
import com.chickiroad.game.game.model.GameState
import com.chickiroad.game.game.model.GameUiState
import com.chickiroad.game.game.model.Lane
import com.chickiroad.game.game.model.Obstacle
import com.chickiroad.game.game.model.Player
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class GameViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState(bestScore = settingsManager.bestScore))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private var gameJob: Job? = null
    private var lastCoinTime = 0L
    private var lastObstacleTime = 0L

    fun startGame() {
        applyShopBonuses()
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            while (_uiState.value.gameState == GameState.PLAYING) {
                updateGame()
                delay(16)
            }
        }
    }

    private fun applyShopBonuses() {
        val currentState = _uiState.value
        val gameBonuses = getGameBonusesFromSettings()

        _uiState.value = currentState.copy(
            scoreMultiplier = gameBonuses.scoreMultiplier,
            maxHealth = gameBonuses.maxHealth,
            baseSpeed = 1f,
            health = gameBonuses.maxHealth,
            isDoubleX2Active = settingsManager.doubleX2Purchased,
            isShieldActive = settingsManager.shieldPurchased,
            isMagnetActive = settingsManager.magnetPurchased
        )
    }

    private fun updateGame() {
        val currentState = _uiState.value

        val allCoins = generateCoins(currentState)
        val allObstacles = generateObstacles(currentState.copy(coins = allCoins))

        val movedCoins = moveCoins(allCoins)
        val movedObstacles = moveObstacles(allObstacles)

        val (collectedCoins, remainingCoins) = checkCoinCollisions(movedCoins, currentState.player)
        val hasObstacleCollision = checkObstacleCollisions(movedObstacles, currentState.player)

        var newScore = currentState.score
        val newSpeed = currentState.speed

        collectedCoins.forEach { coin ->
            newScore += (1 * currentState.currentScoreMultiplier).toInt()
        }

        if (hasObstacleCollision) {
            val newHealth = currentState.health - 1

            val filteredObstacles = movedObstacles.filter { obstacle ->
                val distanceX = kotlin.math.abs(obstacle.x - currentState.player.x)
                val distanceY = kotlin.math.abs(obstacle.y - currentState.player.y)
                val collisionDistance = 45f
                !(distanceX < collisionDistance && distanceY < collisionDistance)
            }

            if (newHealth <= 0) {
                gameOver()
                return
            } else {
                _uiState.value = currentState.copy(
                    coins = remainingCoins,
                    obstacles = filteredObstacles,
                    score = newScore,
                    speed = newSpeed,
                    health = newHealth
                )
                return
            }
        }

        _uiState.value = currentState.copy(
            coins = remainingCoins,
            obstacles = movedObstacles,
            score = newScore,
            speed = newSpeed
        )
    }
    private fun generateObstacles(state: GameUiState): List<Obstacle> {
        val currentTime = Clock.System.now().toEpochMilliseconds()
        val newObstacles = state.obstacles.toMutableList()

        if (currentTime - lastObstacleTime > (2000..4000).random()) {
            val lane = Lane.entries.toTypedArray().random()
            val x = when (lane) {
                Lane.LEFT -> state.leftLaneX
                Lane.CENTER -> state.centerLaneX
                Lane.RIGHT -> state.rightLaneX
            }

            val obstacle = Obstacle(
                id = "obstacle_$currentTime",
                lane = lane,
                x = x,
                y = -100f
            )

            newObstacles.add(obstacle)
            lastObstacleTime = currentTime
        }

        return newObstacles
    }

    private fun moveObstacles(obstacles: List<Obstacle>): List<Obstacle> {
        return obstacles.map { obstacle ->
            obstacle.copy(y = obstacle.y + 3f)
        }.filter { it.y < _uiState.value.screenHeight + 100f }
    }

    private fun checkObstacleCollisions(obstacles: List<Obstacle>, player: Player): Boolean {
        obstacles.forEach { obstacle ->
            val distanceX = kotlin.math.abs(obstacle.x - player.x)
            val distanceY = kotlin.math.abs(obstacle.y - player.y)

            val collisionDistance = 45f
            if (distanceX < collisionDistance && distanceY < collisionDistance) {
                return true
            }
        }
        return false
    }

    private fun generateCoins(state: GameUiState): List<Coin> {
        val currentTime = Clock.System.now().toEpochMilliseconds()
        val newCoins = state.coins.toMutableList()

        if (currentTime - lastCoinTime > (3000..5000).random()) {
            val lane = Lane.entries.toTypedArray().random()
            val x = when (lane) {
                Lane.LEFT -> state.leftLaneX
                Lane.CENTER -> state.centerLaneX
                Lane.RIGHT -> state.rightLaneX
            }

            val coin = Coin(
                id = "coin_$currentTime",
                lane = lane,
                x = x,
                y = -100f
            )

            newCoins.add(coin)
            lastCoinTime = currentTime
        }

        return newCoins
    }

    private fun moveCoins(coins: List<Coin>): List<Coin> {
        return coins.map { coin ->
            coin.copy(y = coin.y + 3f)
        }.filter { it.y < _uiState.value.screenHeight + 100f }
    }

    private fun checkCoinCollisions(coins: List<Coin>, player: Player): Pair<List<Coin>, List<Coin>> {
        val collected = mutableListOf<Coin>()
        val remaining = mutableListOf<Coin>()

        coins.forEach { coin ->
            val distanceX = kotlin.math.abs(coin.x - player.x)
            val distanceY = kotlin.math.abs(coin.y - player.y)

            val collisionDistance = if (_uiState.value.isMagnetActive) 200f else 50f
            val isColliding = distanceX < collisionDistance && distanceY < collisionDistance

            if (isColliding) {
                collected.add(coin)
            } else {
                remaining.add(coin)
            }
        }

        return Pair(collected, remaining)
    }

    fun onScreenSizeChanged(width: Float, height: Float) {
        val newState = _uiState.value.copy(
            screenWidth = width,
            screenHeight = height
        )

        val leftLaneX = newState.leftLaneX
        val playerY = height - GameUiState.PLAYER_HEIGHT - 50f

        _uiState.value = newState.copy(
            player = newState.player.copy(
                x = leftLaneX,
                y = playerY
            )
        )

        if (_uiState.value.gameState == GameState.PLAYING) {
            startGame()
        }
    }

    fun onSwipeLeft() {
        val currentState = _uiState.value
        if (currentState.gameState != GameState.PLAYING) return

        when (currentState.player.lane) {
            Lane.RIGHT -> switchToLane(Lane.CENTER)
            Lane.CENTER -> switchToLane(Lane.LEFT)
            Lane.LEFT -> {}
        }
    }

    fun onSwipeRight() {
        val currentState = _uiState.value
        if (currentState.gameState != GameState.PLAYING) return

        when (currentState.player.lane) {
            Lane.LEFT -> switchToLane(Lane.CENTER)
            Lane.CENTER -> switchToLane(Lane.RIGHT)
            Lane.RIGHT -> {}
        }
    }

    fun onSwipeUp() {
        val currentState = _uiState.value
        if (currentState.gameState != GameState.PLAYING) return

        if (currentState.player.lane != Lane.CENTER) {
            switchToLane(Lane.CENTER)
        }
    }

    private fun switchToLane(newLane: Lane) {
        val currentState = _uiState.value
        val newX = when (newLane) {
            Lane.LEFT -> currentState.leftLaneX
            Lane.CENTER -> currentState.centerLaneX
            Lane.RIGHT -> currentState.rightLaneX
        }

        _uiState.value = currentState.copy(
            player = currentState.player.copy(
                lane = newLane,
                x = newX
            )
        )
    }

    fun pauseGame() {
        gameJob?.cancel()
        _uiState.value = _uiState.value.copy(gameState = GameState.PAUSED)
    }

    fun resumeGame() {
        _uiState.value = _uiState.value.copy(gameState = GameState.PLAYING)
        startGame()
    }

    fun gameOver() {
        val currentScore = _uiState.value.score
        val currentBest = _uiState.value.bestScore

        settingsManager.coins += currentScore

        if (currentScore > currentBest) {
            settingsManager.bestScore = currentScore
            _uiState.value = _uiState.value.copy(
                gameState = GameState.GAME_OVER,
                bestScore = currentScore
            )
        } else {
            _uiState.value = _uiState.value.copy(gameState = GameState.GAME_OVER)
        }
    }

    fun restartGame() {
        gameJob?.cancel()
        lastCoinTime = 0L
        lastObstacleTime = 0L

        val currentState = _uiState.value
        val gameBonuses = getGameBonusesFromSettings()

        val newState = GameUiState(
            screenWidth = currentState.screenWidth,
            screenHeight = currentState.screenHeight,
            bestScore = currentState.bestScore,
            scoreMultiplier = gameBonuses.scoreMultiplier,
            maxHealth = gameBonuses.maxHealth,
            baseSpeed = 1f,
            health = gameBonuses.maxHealth,
            isDoubleX2Active = settingsManager.doubleX2Purchased,
            isShieldActive = settingsManager.shieldPurchased,
            isMagnetActive = settingsManager.magnetPurchased
        )

        val leftLaneX = newState.leftLaneX
        val playerY = newState.screenHeight - GameUiState.PLAYER_HEIGHT - 50f

        _uiState.value = newState.copy(
            player = newState.player.copy(
                x = leftLaneX,
                y = playerY
            )
        )
        startGame()
    }

    private fun getGameBonusesFromSettings(): GameBonuses {
        var scoreMultiplier = 1f
        if (settingsManager.doubleX2Purchased) scoreMultiplier *= 2f

        val maxHealth = if (settingsManager.shieldPurchased) 5 else 3

        return GameBonuses(
            scoreMultiplier = scoreMultiplier,
            maxHealth = maxHealth
        )
    }
}