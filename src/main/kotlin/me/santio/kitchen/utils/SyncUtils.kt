package me.santio.kitchen.utils

import me.santio.kitchen.koin.IsolatedKoinComponent
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.inject

object SyncUtils: IsolatedKoinComponent {
    private val plugin by inject<JavaPlugin>()

    fun sync(block: Runnable) {
        plugin.server.scheduler.runTask(plugin, block)
    }

    fun async(block: Runnable) {
        plugin.server.scheduler.runTaskAsynchronously(plugin, block)
    }

    fun runLater(delay: Long, block: Runnable) {
        plugin.server.scheduler.runTaskLater(plugin, block, delay)
    }

    fun runTimer(delay: Long, period: Long, block: Runnable) {
        plugin.server.scheduler.runTaskTimer(plugin, block, delay, period)
    }
}