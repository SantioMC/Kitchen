package me.santio.kitchen.registries

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.santio.kitchen.components.BlockComponent
import org.bukkit.Location
import org.bukkit.util.Vector
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import java.lang.reflect.Type

class ComponentRegistry: KoinComponent {

    private val gson by inject<Gson>()
    private val components: MutableMap<Vector, BlockComponent> = mutableMapOf()

    /**
     * Register a block component at a location, this will override any block component that is already registered at that location
     * @param location The location to register the block component at
     * @param component The block component to register
     * @return The block component that was registered
     */
    fun register(location: Location, component: BlockComponent): BlockComponent {
        components[location.block.location.toVector()] = component
        return component
    }

    /**
     * Get a block component at a location
     * @param location The location to get the block component at
     * @return The block component at the location, or null if there is no block component at the location
     */
    fun getComponentAt(location: Location): BlockComponent? = components[location.block.location.toVector()]

    /**
     * Remove a block component at a location, effectively unregistering it
     * @param location The location to remove the block component at
     */
    fun remove(location: Location) {
        getComponentAt(location)?.onDestroy()
        components.remove(location.block.location.toVector())
    }

    /**
     * Resets the registry, removing all block components
     */
    fun reset() {
        components.clear()
        File("components.json").delete()
    }

    /**
     * Check if there is a block component at a location
     * @param location The location to check
     * @return Whether there is a block component at the location
     */
    fun isComponent(location: Location): Boolean = getComponentAt(location) != null

    /**
     * Export all data to a file, so it can be recreated later
     */
    fun export() {
        val file = File("components.json")
        if (!file.exists()) file.createNewFile()

        file.writeText(gson.toJson(components))
    }

    /**
     * Import all data from a file, so it can be used, this will also delete the file
     */
    fun import() {
        val file = File("components.json")
        if (!file.exists()) return

        val json = file.readText()

        val type: Type = object : TypeToken<Map<Vector, BlockComponent>>() {}.type
        components.putAll(gson.fromJson(json, type))
        file.delete()
    }

}