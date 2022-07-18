package com.arcieri.wagner.mvvm_recipebook.data

import android.content.Context
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

class RecipeData {



    fun loadRecipe(context: Context): Recipe {
        return CatalogData().loadCatalog(context)[2]
    }

    fun newRecipe(): Recipe{
        return Recipe(
            name = "NEW RECIPE",
        )
    }
}