package me.santio.kitchen.koin

import me.santio.kitchen.Kitchen.Companion.koinApp
import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface IsolatedKoinComponent : KoinComponent {
    override fun getKoin(): Koin = koinApp.koin
}