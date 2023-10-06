package me.santio.kitchen.factory

import me.santio.kitchen.utils.MessageFactory.colored
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.function.Consumer

object ItemFactory {

    private fun ItemStack.meta(callback: Consumer<ItemMeta>): ItemStack {
        val meta = itemMeta
        callback.accept(meta)
        itemMeta = meta
        return this
    }

    fun simple(material: Material, name: String): ItemStack {
        return ItemStack(material).meta {
            it.displayName("<white>$name".colored())
        }
    }

}