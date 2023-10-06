package me.santio.kitchen.listeners

import me.santio.kitchen.registries.ComponentRegistry
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPistonExtendEvent
import org.bukkit.event.block.BlockPistonRetractEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ComponentListener: Listener, KoinComponent {

    private val componentRegistry: ComponentRegistry by inject()

    @EventHandler
    private fun onPistonExtend(event: BlockPistonExtendEvent) {
        for (block in event.blocks) {
            if (componentRegistry.isComponent(block.location)) {
                event.isCancelled = true
                return
            }
        }
    }

    @EventHandler
    private fun onPistonRetract(event: BlockPistonRetractEvent) {
        for (block in event.blocks) {
            if (componentRegistry.isComponent(block.location)) {
                event.isCancelled = true
                return
            }
        }
    }

    @EventHandler
    private fun onBreak(event: BlockBreakEvent) {
        if (!componentRegistry.isComponent(event.block.location)) return
        componentRegistry.remove(event.block.location)
    }

    @EventHandler
    private fun onInteract(event: PlayerInteractEvent) {
        if (event.hand != EquipmentSlot.HAND) return
        if (event.action != Action.RIGHT_CLICK_BLOCK) return

        // Check both the block they're clicking, and the one under it
        val component = componentRegistry.getComponentAt(event.clickedBlock!!.location)
            ?: componentRegistry.getComponentAt(event.clickedBlock!!.getRelative(BlockFace.DOWN).location)
            ?: return

        event.isCancelled = true
        component.onInteract(event.player)
    }

}