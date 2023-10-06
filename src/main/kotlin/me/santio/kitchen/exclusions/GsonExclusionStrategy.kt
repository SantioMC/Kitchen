package me.santio.kitchen.exclusions

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

object GsonExclusionStrategy: ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return false
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return clazz == Lazy::class.java
    }
}