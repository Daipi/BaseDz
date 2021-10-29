package com.daipi.http.common.data.adapter

import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

class DoubleDefaultAdapter : JsonSerializer<Double>, JsonDeserializer<Double> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Double? {
        try {
            if ("" == json.asString || "null" == json.asString) {
                return 0.0
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return try {
            json.asDouble
        } catch (e: Exception) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Double?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement? {
        return JsonPrimitive(src)
    }
}