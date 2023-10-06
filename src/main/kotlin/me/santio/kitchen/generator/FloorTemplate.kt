package me.santio.kitchen.generator

import org.bukkit.Material

interface FloorTemplate {

    fun getBlockAt(x: Int, z: Int): Material

    /**
     * A utility function to create a checkerboard pattern.
     * @param x The x coordinate of the block
     * @param z The z coordinate of the block
     * @param size The size of each square
     * @return True if the block should be one color, false if it should be the other
     */
    fun checkerboard(x: Int, z: Int, size: Int): Boolean {
        return if (x % (size * 2) < size) {
            z % (size * 2) < size
        } else {
            z % (size * 2) >= size
        }
    }

}