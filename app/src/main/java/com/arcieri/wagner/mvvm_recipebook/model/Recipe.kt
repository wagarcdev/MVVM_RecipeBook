package com.arcieri.wagner.mvvm_recipebook.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "recipe_tbl")
data class Recipe (


    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "recipe_name")
    var name: String = "",

    @ColumnInfo(name = "recipe_image_ID", typeAffinity = ColumnInfo.BLOB)
    var image: Bitmap? = null,

    @ColumnInfo(name = "recipe_methods")
    var recipeMethods: MutableList<String> = emptyList<String>().toMutableList(),

    @ColumnInfo(name = "recipe_ingredients")
    var ingredients: MutableList<Ingredient> = emptyList<Ingredient>().toMutableList(),

    @ColumnInfo(name = "recipe_portions")
    var portions: Int = 0,

    @ColumnInfo(name = "recipe_time")
    var recipeTime: Int = 0,

    @ColumnInfo(name = "recipe_bases")
    var baseRecipes: MutableList<String> = emptyList<String>().toMutableList()
)