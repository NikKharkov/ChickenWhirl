package com.chickiroad.game.managers

expect class AudioManager() {
    fun playBackgroundMusic()
    fun stopBackgroundMusic()
    fun playClickSound()
    fun setMusicEnabled(enabled: Boolean)
    fun setSoundEnabled(enabled: Boolean)
    fun release()
}