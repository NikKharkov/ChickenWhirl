package com.chickiroad.game.game.model

data class Coin(
    val id: String,
    val lane: Lane,
    val x: Float,
    val y: Float,
    val size: Float = 60f
)