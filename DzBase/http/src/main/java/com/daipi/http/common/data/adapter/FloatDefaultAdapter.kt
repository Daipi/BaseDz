package com.daipi.http.common.data.adapter

import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

class FloatDefaultAdapter : JsonSerializer<Float?>, JsonDeserializer<Float> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Float {
        try {
            if ("" == json.asString || "null" == json.asString) {
                return 0.0f
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return try {
            json.asFloat
        } catch (e: Exception) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Float?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src)
    }
}