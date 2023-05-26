package com.arcieri.wagner.mvvm_recipebook.data.local

import androidx.room.TypeConverter
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    private val ingredientsAdapter by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listMyData = Types.newParameterizedType(List::class.java, Ingredient::class.java)
        return@lazy moshi.adapter<List<Ingredient>>(listMyData)
    }

    private val recipesAdapter by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listMyData = Types.newParameterizedType(List::class.java, Recipe::class.java)
        return@lazy moshi.adapter<List<Recipe>>(listMyData)
    }


    @TypeConverter
    fun ingredientsToJson(ingredients: List<Ingredient>): String {
        return ingredientsAdapter.toJson(ingredients)
    }

    @TypeConverter
    fun ingredientsFromJson(json: String) : List<Ingredient>? {
        return ingredientsAdapter.fromJson(json)
    }

    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toString(value: String) = Json.decodeFromString<List<String>>(value)


    @TypeConverter
    fun recipesToJson(recipes: List<Recipe>): String {
        return recipesAdapter.toJson(recipes)
    }

    @TypeConverter
    fun recipesFromJson(json: String) : List<Recipe>? {
        return recipesAdapter.fromJson(json)
    }


}