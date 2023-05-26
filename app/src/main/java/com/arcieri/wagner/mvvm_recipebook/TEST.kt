package com.arcieri.wagner.mvvm_recipebook

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun main() {

    val formattedDate = LocalDateTime.now().toLocalDate().dayOfMonth

    Log.i("TAG", "main: $formattedDate")



}