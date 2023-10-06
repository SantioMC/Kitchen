package me.santio.kitchen.components

import org.bukkit.inventory.ItemStack

interface ItemComponent: Component {
    fun getItem(): ItemStack
}