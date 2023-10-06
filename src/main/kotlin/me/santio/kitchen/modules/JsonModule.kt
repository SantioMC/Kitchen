package me.santio.kitchen.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.santio.kitchen.adapters.BlockAdapter
import me.santio.kitchen.adapters.RuntimeTypeAdapterFactory
import me.santio.kitchen.adapters.VectorAdapter
import me.santio.kitchen.components.Component
import me.santio.kitchen.components.ComponentType
import me.santio.kitchen.exclusions.GsonExclusionStrategy
import org.bukkit.block.Block
import org.bukkit.util.Vector
import org.koin.core.module.Module
import org.koin.dsl.module

val jsonModule: Module = module {
    single<Gson> {
        GsonBuilder()
            .registerTypeAdapter(Block::class.java, BlockAdapter)
            .registerTypeAdapter(Vector::class.java, VectorAdapter)
            .setExclusionStrategies(GsonExclusionStrategy)
            .registerTypeAdapterFactory(
                RuntimeTypeAdapterFactory.of(Component::class.java, "_type").apply {
                    recognizeSubtypes()
                    for (component in ComponentType.entries) {
                        registerSubtype(component.clazz, component.name)
                    }
                }
            )
            .create()
    }
}