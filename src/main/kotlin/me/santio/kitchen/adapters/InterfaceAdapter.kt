//package me.santio.kitchen.adapters
//
//import com.google.gson.*
//import java.lang.reflect.Type
//
//class InterfaceAdapter<T: Any>: RuntimeTypeAdapterFactory<T> {
//
//    override fun serialize(src: T, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
//        val jsonObject = JsonObject()
//        jsonObject.addProperty("_type", src::class.java.getName())
//        jsonObject.add("_data", context.serialize(src))
//        return jsonObject
//    }
//
//    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): T {
//        val jsonObject = json.getAsJsonObject()
//        val className = jsonObject.get("_type").asString
//        return context.deserialize(jsonObject.get("_data"), Class.forName(className))
//    }
//
//}