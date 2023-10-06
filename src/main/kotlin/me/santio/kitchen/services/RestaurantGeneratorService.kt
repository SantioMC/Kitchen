package me.santio.kitchen.services

import me.santio.kitchen.generator.FloorTemplate
import org.bukkit.Location

class RestaurantGeneratorService {

    fun generate(location: Location, floor: FloorTemplate, size: Int) {
        val current = location.clone().subtract(size / 2.0, 0.0, size / 2.0)

        for (x in 0..<size) {
            for (z in 0..<size) {
                current.block.type = floor.getBlockAt(x, z)
                current.add(0.0, 0.0, 1.0)
            }
            current.add(1.0, 0.0, -size.toDouble())
        }
    }

}