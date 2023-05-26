package com.arcieri.wagner.mvvm_recipebook.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeBookDatabaseDAO {

    @Query("SELECT * from recipe_tbl")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * from recipe_tbl where id=:id")
    suspend fun getRecipeById(id: Long): Recipe

    @Insert(onConflict = REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * from users_tbl where email=:email")
    suspend fun getUserByEmail(email: String): User

    @Update(onConflict = REPLACE)
    suspend fun updateRecipe(recipe: Recipe)

    @Query("DELETE from recipe_tbl")
    suspend fun deleteAllRecipes()

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)



}
