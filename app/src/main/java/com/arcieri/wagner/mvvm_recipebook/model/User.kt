package com.arcieri.wagner.mvvm_recipebook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_tbl")
data class User(

    @PrimaryKey
    val email: String,
    val userName: String,
    val profileImageFilePath: String?
)