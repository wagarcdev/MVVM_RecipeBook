package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.components.main_screen_content.AppLogo

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BackgroundImageRow() {

    Row(Modifier.fillMaxSize()) { BackgroundContent() }

}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun BackgroundContent() {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            modifier = Modifier
                .systemBarsPadding()
                .padding(top = 72.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppLogo()
        }
    }


}