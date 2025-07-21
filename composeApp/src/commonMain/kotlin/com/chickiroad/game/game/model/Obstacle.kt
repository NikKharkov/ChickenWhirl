package com.chickiroad.game.game.model

data class Obstacle(
    val id: String,
    val lane: Lane,
    val x: Float,
    val y: Float,
    val size: Float = 70f
)
