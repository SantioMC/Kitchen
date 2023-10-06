package me.santio.kitchen.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.bukkit.util.Vector

object VectorAdapter: TypeAdapter<Vector>() {
    override fun write(out: JsonWriter, value: Vector) {
        out.value("${value.x},${value.y},${value.z}")
    }

    override fun read(`in`: JsonReader): Vector {
        val split = `in`.nextString().split(",")
        return Vector(split[0].toDouble(), split[1].toDouble(), split[2].toDouble())
    }
}