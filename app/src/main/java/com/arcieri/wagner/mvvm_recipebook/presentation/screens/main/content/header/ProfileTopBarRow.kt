package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_25
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White
import kotlinx.coroutines.launch

@Composable
fun ProfileTopBarRow(
    scaffoldState: ScaffoldState,
    signInGoogleViewModel: SignInViewModel,
    helloColor: Color
) {

    val  coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                border = BorderStroke(3.dp, RB_Black_25),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(50.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }

                    },
                elevation = 3.dp
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(1f),
                    model =
                    if (signInGoogleViewModel.userProfileImage.value == "") {
                        R.drawable.blank_profile_picture
                    } else {
                        signInGoogleViewModel.userProfileImage.value
                    },
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.Medium
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Hello",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = helloColor,
                textAlign = TextAlign.Start
            )



            Text(
                text = "//TODO get user name", //TODO get user name
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = RB_White,
                textAlign = TextAlign.Start
            )
        }
    }
}