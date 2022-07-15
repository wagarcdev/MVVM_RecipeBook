package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel

@Composable
fun MainMenuButtonsScaffold(
    catalogViewModel: CatalogViewModel,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        GradientTransitionRows(transitionHeight = 50.dp)

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xCC000000),
                            Color(0xFA000000)
                        ),
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            content.invoke()
        }

    }
}