package me.santio.kitchen.generator.impl

import me.santio.kitchen.generator.FloorTemplate
import org.bukkit.Material

object BlueprintFloor: FloorTemplate {
    override fun getBlockAt(x: Int, z: Int): Material {
        return if (checkerboard(x, z, 5)) {
            Material.LIGHT_BLUE_CONCRETE
        } else {
            Material.WHITE_CONCRETE
        }
    }
}