package me.santio.kitchen

import me.santio.kitchen.commands.ComponentCommand
import me.santio.kitchen.commands.GenerateCommand
import me.santio.kitchen.commands.ResetDataCommand
import me.santio.kitchen.listeners.ComponentListener
import me.santio.kitchen.listeners.InventoryListener
import me.santio.kitchen.modules.jsonModule
import me.santio.kitchen.modules.playerModule
import me.santio.kitchen.modules.restaurantModule
import me.santio.kitchen.registries.ComponentRegistry
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class Kitchen: JavaPlugin(), KoinComponent {

    override fun onEnable() {
        koinApp = startKoin {
            modules(restaurantModule)
            modules(playerModule)
            modules(jsonModule)
            modules(module {
                single<JavaPlugin> {
                    Bukkit.getPluginManager().getPlugin("Kitchen") as JavaPlugin
                }
            })
        }

        getCommand("generate")?.setExecutor(GenerateCommand)
        getCommand("component")?.setExecutor(ComponentCommand)
        getCommand("resetdata")?.setExecutor(ResetDataCommand)

        server.pluginManager.registerEvents(InventoryListener, this)
        server.pluginManager.registerEvents(ComponentListener, this)

        koinApp.koin.get<ComponentRegistry>().apply { import() }
    }

    override fun onDisable() {
        koinApp.koin.get<ComponentRegistry>().apply { export() }
    }

    companion object {
        lateinit var koinApp: KoinApplication
    }

}