package com.arcieri.wagner.mvvm_recipebook.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeViewModel @Inject constructor (private val repository: RecipeRepository): ViewModel() {

    lateinit var navController: NavController

    private var _ingredientList = MutableStateFlow<List<Ingredient>>(emptyList())
    val ingredientList = _ingredientList.asStateFlow()

    private var _methodList = MutableStateFlow<List<String>>(emptyList())
    val methodList = _methodList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {

        }
    }

//    name
//
//    image
//
//    recipeMethods
//
//    ingredients
//
//    portions
//
//    recipeTime
//
//    baseRecipes




}