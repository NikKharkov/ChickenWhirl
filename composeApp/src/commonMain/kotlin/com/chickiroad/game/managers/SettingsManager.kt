package com.chickiroad.game.managers

import com.russhwolf.settings.Settings

class SettingsManager {
    val settings = Settings()

    var isMusicEnabled: Boolean
        get() = settings.getBoolean(KEY_MUSIC_ENABLED, true)
        set(value) = settings.putBoolean(KEY_MUSIC_ENABLED, value)

    var isSoundEnabled: Boolean
        get() = settings.getBoolean(KEY_SOUND_ENABLED, true)
        set(value) = settings.putBoolean(KEY_SOUND_ENABLED, value)

    var bestScore: Int
        get() = settings.getInt(KEY_BEST_SCORE, 0)
        set(value) = settings.putInt(KEY_BEST_SCORE, value)

    var doubleX2Purchased: Boolean
        get() = settings.getBoolean(KEY_DOUBLE_X2_PURCHASED, false)
        set(value) = settings.putBoolean(KEY_DOUBLE_X2_PURCHASED, value)

    var shieldPurchased: Boolean
        get() = settings.getBoolean(KEY_SHIELD_PURCHASED, false)
        set(value) = settings.putBoolean(KEY_SHIELD_PURCHASED, value)

    var magnetPurchased: Boolean
        get() = settings.getBoolean(KEY_MAGNET_PURCHASED, false)
        set(value) = settings.putBoolean(KEY_MAGNET_PURCHASED, value)

    var coins: Int
        get() = settings.getInt(KEY_COINS, 0)
        set(value) = settings.putInt(KEY_COINS, value)

    companion object {
        private const val KEY_MUSIC_ENABLED = "music_enabled"
        private const val KEY_SOUND_ENABLED = "sound_enabled"
        private const val KEY_BEST_SCORE = "best_score"
        private const val KEY_DOUBLE_X2_PURCHASED = "double_x2_purchased"
        private const val KEY_SHIELD_PURCHASED = "shield_purchased"
        private const val KEY_MAGNET_PURCHASED = "magnet_purchased"
        private const val KEY_COINS = "coins"
    }
}