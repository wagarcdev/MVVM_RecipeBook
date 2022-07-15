package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_screen_content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R

@Composable
fun AppLogo() {

    Image(
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .padding(0.dp),
        painter = painterResource(id = R.drawable.techchef_lab_logo_white),
        contentDescription = "App Logo",
        contentScale = ContentScale.FillWidth

    )
}