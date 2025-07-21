package com.chickiroad.game.game.model

data class GameUiState(
    val gameState: GameState = GameState.PLAYING,
    val player: Player = Player(),
    val obstacles: List<Obstacle> = emptyList(),
    val coins: List<Coin> = emptyList(),
    val bestScore: Int = 0,
    val score: Int = 0,
    val speed: Float = 1f,
    val health: Int = 3,
    val screenWidth: Float = 0f,
    val screenHeight: Float = 0f,
    val scoreMultiplier: Float = 1f,
    val maxHealth: Int = 3,
    val baseSpeed: Float = 1f,
    val isDoubleX2Active: Boolean = false,
    val isShieldActive: Boolean = false,
    val isMagnetActive: Boolean = false,
) {
    companion object {
        const val ROAD_WIDTH = 330f
        const val LANE_WIDTH = 110f
        const val PLAYER_SIZE = 80f
        const val PLAYER_HEIGHT = 100f
    }

    val leftLaneX: Float
        get() = (screenWidth / 2f) - (ROAD_WIDTH / 2f) + (LANE_WIDTH / 2f) - (PLAYER_SIZE / 2f)

    val centerLaneX: Float
        get() = (screenWidth / 2f) - (PLAYER_SIZE / 2f)

    val rightLaneX: Float
        get() = (screenWidth / 2f) + (ROAD_WIDTH / 2f) - (LANE_WIDTH / 2f) - (PLAYER_SIZE / 2f)

    val currentScoreMultiplier: Float
        get() = if (isDoubleX2Active) scoreMultiplier * 2f else scoreMultiplier

    val isProtected: Boolean
        get() = isShieldActive
}