package me.santio.kitchen.modules

import me.santio.kitchen.registries.ComponentRegistry
import me.santio.kitchen.services.RestaurantGeneratorService
import org.koin.core.module.Module
import org.koin.dsl.module

val restaurantModule: Module = module {
    single { RestaurantGeneratorService() }
    single { ComponentRegistry() }
}