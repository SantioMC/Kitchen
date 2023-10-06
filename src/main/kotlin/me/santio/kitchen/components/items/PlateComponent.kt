package me.santio.kitchen.components.items

import me.santio.kitchen.components.ItemComponent
import me.santio.kitchen.factory.ItemFactory
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class PlateComponent: ItemComponent {

    override fun getItem(): ItemStack {
        return ItemFactory.simple(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, "Plate")
    }

}