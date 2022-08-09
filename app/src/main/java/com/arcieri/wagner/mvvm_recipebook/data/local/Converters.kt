package com.arcieri.wagner.mvvm_recipebook.data.local

import androidx.room.TypeConverter
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    private val adapter by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listMyData = Types.newParameterizedType(List::class.java, Ingredient::class.java)
        return@lazy moshi.adapter<List<Ingredient>>(listMyData)
    }

    @TypeConverter
    fun toJson(ingredients: List<Ingredient>): String {
        return adapter.toJson(ingredients)
    }

    @TypeConverter
    fun fromJson(json: String) : List<Ingredient>? {
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value)




    @TypeConverter
    fun toString(value: String) = Json.decodeFromString<List<String>>(value)


}