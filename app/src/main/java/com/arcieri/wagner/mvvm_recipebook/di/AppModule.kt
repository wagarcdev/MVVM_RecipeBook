package com.arcieri.wagner.mvvm_recipebook.di

import android.content.Context
import androidx.room.Room
import com.arcieri.wagner.mvvm_recipebook.data.local.RecipeBookDatabaseDAO
import com.arcieri.wagner.mvvm_recipebook.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesRecipeDAO(recipeDatabase: RecipeDatabase): RecipeBookDatabaseDAO = recipeDatabase.recipeDAO()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): RecipeDatabase
     = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        "recipes_db"
     ).fallbackToDestructiveMigration()
        .createFromAsset("database/recipes_db.db")
        .build()
}