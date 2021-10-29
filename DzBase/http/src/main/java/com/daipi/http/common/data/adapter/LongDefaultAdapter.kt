package com.daipi.http.common.data.adapter

import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

class LongDefaultAdapter : JsonSerializer<Long>, JsonDeserializer<Long> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Long? {
        try {
            if ("" == json.asString || "null" == json.asString) {
                return 0L
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return try {
            json.asLong
        } catch (e: Exception) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(src: Long?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return JsonPrimitive(src)
    }
}