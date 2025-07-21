package com.chickiroad.game.di

import com.chickiroad.game.game.presentation.GameViewModel
import com.chickiroad.game.managers.AudioManager
import com.chickiroad.game.managers.SettingsManager
import com.chickiroad.game.shop.presentation.ShopViewModel
import com.chickiroad.game.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::SettingsManager)
    singleOf(::AudioManager)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::GameViewModel)
    viewModelOf(::ShopViewModel)
}