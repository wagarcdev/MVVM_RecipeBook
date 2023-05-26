package com.arcieri.wagner.mvvm_recipebook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arcieri.wagner.mvvm_recipebook.model.RecentRecipes
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.model.User

@Database(
    entities = [
        Recipe::class,
        User::class,
        RecentRecipes::class
    ],
    version = 15,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun recipeDAO(): RecipeBookDatabaseDAO

}