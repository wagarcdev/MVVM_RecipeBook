package com.arcieri.wagner.mvvm_recipebook.repository

import com.arcieri.wagner.mvvm_recipebook.data.RecipeDatabaseDAO
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeDatabaseDAO: RecipeDatabaseDAO) {

    suspend fun addRecipe(recipe: Recipe) = recipeDatabaseDAO.insertRecipe(recipe)

    suspend fun updateRecipe(recipe: Recipe) = recipeDatabaseDAO.updateRecipe(recipe)

    suspend fun deleteRecipe(recipe: Recipe) = recipeDatabaseDAO.deleteRecipe(recipe)

    suspend fun deleteAllRecipes() = recipeDatabaseDAO.deleteAllRecipes()

    fun getAllRecipes(): Flow<List<Recipe>> =
        recipeDatabaseDAO.getRecipes().flowOn(Dispatchers.IO).conflate()

}