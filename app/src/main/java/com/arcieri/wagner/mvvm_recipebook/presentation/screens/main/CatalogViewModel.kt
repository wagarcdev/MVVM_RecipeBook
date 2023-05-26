package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class CatalogViewModel @Inject constructor (
    private val repository: RecipeBookRepository
): ViewModel() {

    var recipeList = MutableStateFlow<List<Recipe>>(emptyList())
        private set

    var test = arrayListOf<String>()


    var currentRecipe by mutableStateOf<Recipe?>(
        null
    )
        private set

    private var _recipeTime = MutableStateFlow(0)
    val recipeTime = _recipeTime.asStateFlow()

    private var recipeMinutes = 0
    private var recipeHours = 0

    init {

        viewModelScope.launch {

            /**  ADD Default Catalog to generate DB file
             * ONLY use on First Build/Run of DB version */
//            viewModelScope.launch { defaultCatalog.forEach { addRecipe(it) } }.join()
            /** ---- */
            getAllRecipes()
        }

    }

    private fun getAllRecipes() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllRecipes().distinctUntilChanged().collect { listOfRecipes ->
                if (listOfRecipes.isEmpty()) {
                    Log.d("CATALOG", "EMPTY LIST OF RECIPES ")
                } else {
                    recipeList.value = listOfRecipes
                }
            }
        }
    }


    fun changeMinutes(minutes: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            recipeMinutes = minutes
            _recipeTime.value = recipeMinutes + (recipeHours * 60)
        }
    }

    fun changeHours(hours: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            recipeHours = hours
            _recipeTime.value = recipeMinutes + (recipeHours * 60)
        }
    }

    fun setRecipe(recipe: Recipe) {
        currentRecipe = recipe
    }

    fun newRecipe(): Long {
        viewModelScope.launch {
            addRecipe(
                Recipe(name = "NEW RECIPE")
            )
        }

        return -1L
    }








    suspend fun addCatalog(recipesList: List<Recipe>) {
        viewModelScope.launch {
            recipesList.forEach { recipe ->

                repository.addRecipe(recipe)

            }
        }
    }

    suspend fun addRecipe(recipe: Recipe): Long {

        var recipeId: Long = -1

        recipeId = repository.addRecipe(recipe)


        return recipeId
    }



    suspend fun updateRecipe(recipe: Recipe) = viewModelScope.launch { repository.updateRecipe(recipe) }
    suspend fun removeRecipe(recipe: Recipe) = viewModelScope.launch { repository.deleteRecipe(recipe) }
    suspend fun removeAllRecipes() = viewModelScope.launch { repository.deleteAllRecipes() }

    suspend fun getRecipe(recipeId: Long): Recipe {
        return repository.getRecipe(recipeId)
    }



}

