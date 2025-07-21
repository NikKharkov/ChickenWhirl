package com.chickiroad.game.shop.model

import org.jetbrains.compose.resources.DrawableResource

data class ShopItem(
    val id: String,
    val price: Int,
    val icon: DrawableResource,
    val isPurchased: Boolean
)
