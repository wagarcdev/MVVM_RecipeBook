package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.components.main_menu_content.main_menu_buttons.units_converter.weight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_YellowDarkUltra
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_YellowLightExtra

@Composable
fun WeightConverterDialog(isWeightOpen: MutableState<Boolean>) {

    ShowAlertDialog(
        isDialogOpen = isWeightOpen,
        defaultBottomBar = true,
        dialogContent = { WeightConverterDialogContent() },
        dialogColor = RB_YellowDarkUltra,
        title = "Weight Converter",
        titleColor = RB_YellowLightExtra,
        titleSize = 20.sp,
        titleWeight = FontWeight.Bold
    )

}

