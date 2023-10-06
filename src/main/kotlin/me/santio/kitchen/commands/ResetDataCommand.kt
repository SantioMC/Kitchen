package me.santio.kitchen.commands

import me.santio.kitchen.registries.ComponentRegistry
import me.santio.kitchen.utils.MessageFactory.colored
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ResetDataCommand: CommandExecutor, KoinComponent {

    private val registry: ComponentRegistry by inject()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        registry.reset()
        sender.sendMessage("<red>All registry data has been reset".colored())

        return true
    }

}