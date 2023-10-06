package me.santio.kitchen.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.block.Block

object BlockAdapter: TypeAdapter<Block>() {
    override fun write(out: JsonWriter, value: Block) {
        out.value("${value.world.name}:${value.x}:${value.y}:${value.z}")
    }

    override fun read(`in`: JsonReader): Block {
        val split = `in`.nextString().split(":")
        return Location(
            Bukkit.getWorld(split[0]),
            split[1].toDouble(),
            split[2].toDouble(),
            split[3].toDouble()
        ).block
    }
}