package me.santio.kitchen.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

object MessageFactory {

    fun String.colored(): Component {
        return MiniMessage.miniMessage().deserialize(this)
    }

}