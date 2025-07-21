package com.chickiroad.game.shop.presentation

import androidx.lifecycle.ViewModel
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.ic_magnet
import chickenwhirl.composeapp.generated.resources.ic_shield
import chickenwhirl.composeapp.generated.resources.ic_x2
import com.chickiroad.game.managers.SettingsManager
import com.chickiroad.game.shop.model.ShopItem
import com.chickiroad.game.shop.model.ShopUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShopViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    init {
        updateShopState()
    }

    private fun updateShopState() {
        val items = listOf(
            ShopItem(
                id = "double_x2",
                price = 500,
                icon = Res.drawable.ic_x2,
                isPurchased = settingsManager.doubleX2Purchased
            ),
            ShopItem(
                id = "shield",
                price = 300,
                icon = Res.drawable.ic_shield,
                isPurchased = settingsManager.shieldPurchased
            ),
            ShopItem(
                id = "magnet",
                price = 200,
                icon = Res.drawable.ic_magnet,
                isPurchased = settingsManager.magnetPurchased
            )
        )

        _uiState.value = _uiState.value.copy(
            items = items,
            coins = settingsManager.coins
        )
    }

    fun purchaseItem(itemId: String) {
        val currentState = _uiState.value
        val item = currentState.items.find { it.id == itemId } ?: return

        if (item.isPurchased || currentState.coins < item.price) return

        when (itemId) {
            "double_x2" -> {
                settingsManager.doubleX2Purchased = true
                settingsManager.coins -= item.price
            }
            "shield" -> {
                settingsManager.shieldPurchased = true
                settingsManager.coins -= item.price
            }
            "magnet" -> {
                settingsManager.magnetPurchased = true
                settingsManager.coins -= item.price
            }
        }

        updateShopState()
    }
}