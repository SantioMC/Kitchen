package me.santio.kitchen.commands

import me.santio.kitchen.components.ComponentType
import me.santio.kitchen.utils.MessageFactory.colored
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

object ComponentCommand: CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (
            args.isEmpty()
            || args.size < 2
            || args[0].lowercase() !in listOf("place", "get")
            || ComponentType.get(args[1]) == null
        ) {
            sender.sendMessage("<red>Usage: /component <place|get> <component>".colored())
            return true
        }

        val componentType = ComponentType.get(args[1])!!

        try {
            if (args[0].lowercase() == "place") {
                componentType.place(sender.location)
                sender.sendMessage("<gray>Successfully placed component <yellow>${componentType.name.lowercase()}<gray>!".colored())
            } else {
                sender.inventory.addItem(componentType.item())
                sender.sendMessage("<gray>You were given a(n) <yellow>${componentType.name.lowercase()}<gray>!".colored())
            }
        } catch (e: IllegalArgumentException) {
            sender.sendMessage("<red>This component cannot be used in this way!".colored())
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String> {
        return when (args?.size ?: 0) {
            1 -> listOf("place", "get")
            2 -> ComponentType.entries.map { it.name.lowercase() }
            else -> listOf()
        }.filter { it.startsWith(args?.get(args.size - 1) ?: "", true) }.toMutableList()
    }
}