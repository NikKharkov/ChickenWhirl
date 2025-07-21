package com.chickiroad.game.ui.screens.settings

import androidx.lifecycle.ViewModel
import com.chickiroad.game.managers.AudioManager
import com.chickiroad.game.managers.SettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SettingsState(
    val isMusicEnabled: Boolean = true,
    val isSoundEnabled: Boolean = true
)

class SettingsViewModel(
    private val settings: SettingsManager,
    private val audioManager: AudioManager
) : ViewModel() {

    private val _state = MutableStateFlow(
        SettingsState(
            isMusicEnabled = settings.isMusicEnabled,
            isSoundEnabled = settings.isSoundEnabled
        )
    )
    val state = _state.asStateFlow()

    fun toggleMusic() {
        _state.update { currentState ->
            val newValue = !currentState.isMusicEnabled
            audioManager.setMusicEnabled(newValue)

            if (newValue) {
                audioManager.playBackgroundMusic()
            } else {
                audioManager.stopBackgroundMusic()
            }

            currentState.copy(isMusicEnabled = newValue)
        }
    }

    fun toggleSound() {
        _state.update { currentState ->
            val newValue = !currentState.isSoundEnabled
            audioManager.setSoundEnabled(newValue)
            currentState.copy(isSoundEnabled = newValue)
        }
    }

    fun playClickSound() {
        audioManager.playClickSound()
    }

    fun startBackgroundMusic() {
        audioManager.playBackgroundMusic()
    }

    fun resetProgress() {
        settings.coins = 0
        settings.doubleX2Purchased = false
        settings.shieldPurchased = false
        settings.magnetPurchased = false
    }

    override fun onCleared() {
        super.onCleared()
        audioManager.release()
    }
}
