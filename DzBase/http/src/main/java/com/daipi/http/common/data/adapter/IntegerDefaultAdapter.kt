package com.daipi.http.common.data.adapter

import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

class IntegerDefaultAdapter : JsonSerializer<Int?>, JsonDeserializer<Int> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Int {
        try {
            if ("" == json.asString || "null" == json.asString) {
                return 0
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            return json.asInt
        } catch (e: Exception) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Int?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src)
    }



}