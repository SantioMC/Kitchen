package me.santio.kitchen.components

import me.santio.kitchen.components.blocks.CounterComponent
import me.santio.kitchen.components.items.PlateComponent
import me.santio.kitchen.registries.ComponentRegistry
import org.bukkit.Location
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

enum class ComponentType (val clazz: Class<out Component>): KoinComponent {
    COUNTER(CounterComponent::class.java),
    PLATE(PlateComponent::class.java),
    ;

    private val registry by inject<ComponentRegistry>()

    /**
     * Places the component at the given location
     * @param loc The location to place the component at
     * @return The component that was placed
     * @throws IllegalArgumentException If the component is not a block component
     */
    fun place(loc: Location): BlockComponent {
        val component = clazz.getDeclaredConstructor().newInstance()
        if (component !is BlockComponent) throw IllegalArgumentException("Cannot place non-block component")

        loc.block.blockData = component.blockType()
        component.associatedBlock = loc.block
        component.onPlace()

        println("Placed component $component at $loc")

        registry.register(loc, component)
        return component
    }

    /**
     * Get an item representation of the component
     * @return The item representation of the component
     * @throws IllegalArgumentException If the component is not an item component
     */
    fun item(): ItemStack {
        val component = clazz.getDeclaredConstructor().newInstance()
        if (component !is ItemComponent) throw IllegalArgumentException("Cannot place non-block component")

        val item = component.getItem()

        val meta = item.itemMeta
        meta.persistentDataContainer.set(ComponentIdentifier.COMPONENT_ID, PersistentDataType.STRING, name.uppercase())
        item.itemMeta = meta

        return item
    }

    companion object {
        /**
         * Gets a component type from the given name
         * @param name The name of the component type
         * @return The component type, or null if it isn't found
         */
        fun get(name: String): ComponentType? {
            return try {
                valueOf(name.uppercase().replace(" ", "_"))
            } catch (e: IllegalArgumentException) {
                null
            }
        }

        /**
         * Gets a component type from the given item
         * @param item The item to get the component type from
         * @return The component type, or null if it isn't found
         */
        fun of(item: ItemStack): ComponentType? {
            val meta = item.itemMeta ?: return null
            val id = meta.persistentDataContainer.get(ComponentIdentifier.COMPONENT_ID, PersistentDataType.STRING) ?: return null
            return get(id)
        }
    }

}
