package com.arcieri.wagner.mvvm_recipebook.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import com.arcieri.wagner.mvvm_recipebook.data.CatalogData
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel

fun getDefaultDatabase(catalogViewModel: CatalogViewModel, context: Context) {

    val sharedPref: SharedPreferences = context.getSharedPreferences(
        "com.example.android.your_application",
        ComponentActivity.MODE_PRIVATE
    )

    val token: String? = sharedPref.getString("token", null)
    if (token == "False" || token == null) {
        // rest of the FirstTime Logic here

        val defaultCatalog = CatalogData().loadCatalog(context)

        suspend { catalogViewModel.addCatalog(defaultCatalog) }


        sharedPref.edit().putString("token", "true").apply()
    } else {
        // rest of the Not-FirstTime Logic here
    }
}