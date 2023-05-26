package com.arcieri.wagner.mvvm_recipebook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recipe_tbl")
data class Recipe (


    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "recipe_name")
    var name: String = "",

    @ColumnInfo(name = "recipe_filepath")
    var imageFilepath: String? = null,

    @ColumnInfo(name = "recipe_methods")
    var recipeMethods: List<String> = emptyList(),

    @ColumnInfo(name = "recipe_ingredients")
    var ingredients: List<Ingredient> = emptyList(),

    @ColumnInfo(name = "recipe_portions")
    var portions: Int = 0,

    @ColumnInfo(name = "recipe_time")
    var recipeTimeInMinutes: Int = 0,

    @ColumnInfo(name = "recipe_bases")
    var baseRecipes: List<String> = emptyList()
)