package com.arcieri.wagner.mvvm_recipebook.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.repository.RecipeBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeBookViewModel @Inject constructor (private val repository: RecipeBookRepository): ViewModel() {

    //var recipe = RecipeData().loadRecipe(context)

    lateinit var navHostController: NavHostController

    private var _recipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeList = _recipeList.asStateFlow()

    init {

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
    fun getAllRecipes() = viewModelScope.launch { repository.getAllRecipes() }





}