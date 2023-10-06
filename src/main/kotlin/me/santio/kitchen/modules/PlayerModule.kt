package me.santio.kitchen.modules

import me.santio.kitchen.services.PlayerService
import me.santio.kitchen.services.RestaurantGeneratorService
import org.koin.core.module.Module
import org.koin.dsl.module

val playerModule: Module = module {
    single { PlayerService() }
}