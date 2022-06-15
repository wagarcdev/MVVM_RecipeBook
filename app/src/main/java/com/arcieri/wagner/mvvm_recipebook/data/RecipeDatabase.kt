package com.arcieri.wagner.mvvm_recipebook.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Database(entities = [Recipe::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun recipeDAO(): RecipeBookDatabaseDAO

}