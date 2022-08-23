package com.arcieri.wagner.mvvm_recipebook.data.local

import androidx.room.*
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeBookDatabaseDAO {

    @Query("SELECT * from recipe_tbl")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * from recipe_tbl where id=:id")
    suspend fun getRecipeById(id: Long): Recipe

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipe(recipe: Recipe)

    @Query("DELETE from recipe_tbl")
    suspend fun deleteAllRecipes()

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)



}
