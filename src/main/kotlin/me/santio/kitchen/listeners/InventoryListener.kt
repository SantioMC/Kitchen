package me.santio.kitchen.listeners

import me.santio.kitchen.services.PlayerService
import org.bukkit.GameMode
import org.bukkit.entity.HumanEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerGameModeChangeEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object InventoryListener: Listener, KoinComponent {

    private val playerService: PlayerService by inject()

    private fun bypassable(player: HumanEntity, func: () -> Unit) {
        if (player.gameMode == GameMode.CREATIVE) return
        func()
    }

    @EventHandler
    private fun onInventoryClick(event: InventoryClickEvent) = bypassable(event.whoClicked) {
        event.isCancelled = true
    }

    @EventHandler
    private fun onSwapHands(event: PlayerSwapHandItemsEvent) = bypassable(event.player) {
        event.isCancelled = true
    }

    @EventHandler
    private fun onSwitchSlot(event: PlayerItemHeldEvent) = bypassable(event.player) {
        if (event.newSlot == 4) return@bypassable
        event.isCancelled = true
    }

    @EventHandler
    private fun onGamemodeChange(event: PlayerGameModeChangeEvent) {
        if (event.newGameMode == GameMode.SURVIVAL || event.newGameMode == GameMode.ADVENTURE) {
            if (event.newGameMode == GameMode.SURVIVAL) {
                event.isCancelled = true
                event.player.gameMode = GameMode.ADVENTURE
            } else playerService.reset(event.player)
        } else if (event.newGameMode == GameMode.CREATIVE && event.player.gameMode == GameMode.ADVENTURE) {
            event.player.inventory.clear()
        }
    }

    @EventHandler
    private fun onJoin(event: PlayerJoinEvent) {
        event.player.gameMode = GameMode.ADVENTURE
        playerService.reset(event.player)
    }

}