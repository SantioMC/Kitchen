package me.santio.kitchen.services

import me.santio.kitchen.components.ComponentType
import me.santio.kitchen.factory.ItemFactory
import org.bukkit.Material
import org.bukkit.entity.Player

class PlayerService {

    fun reset(player: Player) {
        player.fireTicks = 0
        player.health = 20.0
        player.foodLevel = 20
        player.exp = 0.0f
        player.level = 0
        player.inventory.clear()
        player.allowFlight = false
        player.isFlying = false
        player.activePotionEffects.forEach { player.removePotionEffect(it.type) }

        player.inventory.heldItemSlot = 4

        for (i in 0..8) {
            if (i == 4) continue
            player.inventory.setItem(i, ItemFactory.simple(Material.BARRIER, "<reset><red>Blocked Slot"))
        }
    }

    fun isHolding(player: Player, component: ComponentType): Boolean {
        return ComponentType.of(player.inventory.itemInMainHand) == component
    }

}