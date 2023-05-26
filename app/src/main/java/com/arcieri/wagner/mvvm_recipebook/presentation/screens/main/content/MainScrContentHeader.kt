package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.components.ProfileTopBarRow
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.header.SearchBarRow
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_25
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_50
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_75
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent

@Composable
fun MainScrContentHeader(
    scaffoldState: ScaffoldState,
    signInGoogleViewModel: SignInViewModel,
    secondaryVariantFontColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(RB_Black_75),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            RB_Black_75,
                            RB_Black_75,
                            RB_Black_50,
                            RB_Black_25,
                            RB_Transparent
                        ),
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ProfileTopBarRow(scaffoldState, signInGoogleViewModel, secondaryVariantFontColor)

                SearchBarRow()
            }

        }
    }
}