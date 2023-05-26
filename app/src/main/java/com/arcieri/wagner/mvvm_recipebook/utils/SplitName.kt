package com.arcieri.wagner.mvvm_recipebook.utils

fun splitName(name: String): Pair<String?, String?> {
    val names = name.trim().split(Regex("\\s+"))
    return names.firstOrNull() to names.lastOrNull()
}