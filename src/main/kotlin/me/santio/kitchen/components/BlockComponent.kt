package me.santio.kitchen.components

import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player

interface BlockComponent: Component {
    var associatedBlock: Block?
    fun blockType(): BlockData

    fun onPlace() {}
    fun onDestroy() {}
    fun onInteract(player: Player) {}

}