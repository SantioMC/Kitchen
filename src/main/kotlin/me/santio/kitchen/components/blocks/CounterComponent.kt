package me.santio.kitchen.components.blocks

import me.santio.kitchen.components.BlockComponent
import me.santio.kitchen.components.ComponentType
import me.santio.kitchen.services.PlayerService
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CounterComponent: BlockComponent, KoinComponent {
    override var associatedBlock: Block? = null
    private val playerService by inject<PlayerService>()

    private var hasPlate = false

    override fun blockType(): BlockData {
        return Material.SMOOTH_STONE.createBlockData()
    }

    override fun onDestroy() {
        associatedBlock?.getRelative(BlockFace.UP)?.type = Material.AIR
    }

    override fun onInteract(player: Player) {
        if (hasPlate && player.inventory.itemInMainHand.type.isAir) {
            hasPlate = false
            player.inventory.addItem(ComponentType.PLATE.item())

            associatedBlock?.getRelative(BlockFace.UP)?.type = Material.AIR
        } else if (!hasPlate && playerService.isHolding(player, ComponentType.PLATE)) {
            hasPlate = true
            player.inventory.removeItem(ComponentType.PLATE.item())

            associatedBlock?.getRelative(BlockFace.UP)?.type = Material.HEAVY_WEIGHTED_PRESSURE_PLATE
        }
    }

}