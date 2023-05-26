package com.arcieri.wagner.mvvm_recipebook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_tbl")
data class RecentRecipes(

    @PrimaryKey
    val id: Int?,
    val list: List<Recipe>
)