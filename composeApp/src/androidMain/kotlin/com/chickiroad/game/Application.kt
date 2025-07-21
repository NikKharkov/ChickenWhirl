package com.chickiroad.game

import android.app.Application
import com.chickiroad.game.di.initConfig
import org.koin.android.ext.koin.androidContext

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        initConfig {
            androidContext(this@Application)
        }
    }
}