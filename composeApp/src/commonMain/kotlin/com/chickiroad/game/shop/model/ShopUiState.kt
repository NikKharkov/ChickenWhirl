package com.chickiroad.game.shop.model

data class ShopUiState(
    val items: List<ShopItem> = emptyList(),
    val coins: Int = 0
)
