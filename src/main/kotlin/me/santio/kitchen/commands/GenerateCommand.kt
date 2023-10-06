package me.santio.kitchen.commands

import me.santio.kitchen.generator.impl.BlueprintFloor
import me.santio.kitchen.services.RestaurantGeneratorService
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GenerateCommand: CommandExecutor, KoinComponent {

    private val generatorService: RestaurantGeneratorService by inject()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        generatorService.generate(sender.location, BlueprintFloor, 100)
        return true
    }

}