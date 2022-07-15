package com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.repository.RecipeBookRepository
import com.arcieri.wagner.mvvm_recipebook.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor (
    private val repository: RecipeBookRepository
): ViewModel() {

    lateinit var navHostController: NavHostController
    lateinit var navController: NavController

    private var _recipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeList = _recipeList.asStateFlow()

    var recipe by mutableStateOf(Recipe(name = "NEW RECIPE"))
        private set

    fun currentRecipe(currentRecipe: Recipe) {
        recipe = currentRecipe
    }

    fun newRecipe() {
        recipe = Recipe(name = "NEW RECIPE")
    }


    init {

        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllRecipes().distinctUntilChanged().collect { listOfRecipes ->
                if (listOfRecipes.isEmpty()) {
                    Log.d("CATALOG", "EMPTY LIST OF RECIPES ")
                } else {
                    _recipeList.value = listOfRecipes
                }
            }
        }
    }

    suspend fun getRecipeInfo(recipeId: Long): Resource<Recipe> {
        return repository.getRecipeInfo(recipeId)
    }


    suspend fun addCatalog(recipesList: List<Recipe>) {
        recipesList.forEach { recipe ->
            viewModelScope.launch {
                repository.addRecipe(recipe)
            }
        }
    }

    suspend fun addRecipe(recipe: Recipe) = viewModelScope.launch { repository.addRecipe(recipe) }
    suspend fun updateRecipe(recipe: Recipe) = viewModelScope.launch { repository.updateRecipe(recipe) }
    suspend fun removeRecipe(recipe: Recipe) = viewModelScope.launch { repository.deleteRecipe(recipe) }
    suspend fun removeAllRecipes() = viewModelScope.launch { repository.deleteAllRecipes() }

    suspend fun getRecipe(recipeId: Long): Recipe {
        return repository.getRecipe(recipeId)
    }





}