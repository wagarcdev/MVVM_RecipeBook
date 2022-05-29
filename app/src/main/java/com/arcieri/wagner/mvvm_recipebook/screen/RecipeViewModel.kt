package com.arcieri.wagner.mvvm_recipebook.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor (private val repository: RecipeRepository): ViewModel() {

    //var recipe = RecipeData().loadRecipe(context)

    private var _recipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeList = _recipeList.asStateFlow()

    init {
//         recipeList.addAll(RecipeData().loadRecipe(context))

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllRecipes().distinctUntilChanged().collect { listOfRecipes ->
                if (listOfRecipes.isNullOrEmpty()) {
                    Log.d("CATALOG", "EMPTY LIST OF RECIPES ")
                } else {
                    _recipeList.value = listOfRecipes
                }

            }
        }
    }

    suspend fun addRecipe(recipe: Recipe) = viewModelScope.launch { repository.addRecipe(recipe) }
    suspend fun updateRecipe(recipe: Recipe) = viewModelScope.launch { repository.updateRecipe(recipe) }
    suspend fun removeRecipe(recipe: Recipe) = viewModelScope.launch { repository.deleteRecipe(recipe) }
    suspend fun removeAllRecipes() = viewModelScope.launch { repository.deleteAllRecipes() }




}