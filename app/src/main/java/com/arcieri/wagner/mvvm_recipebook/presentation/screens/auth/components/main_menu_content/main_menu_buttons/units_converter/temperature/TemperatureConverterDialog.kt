package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.components.main_menu_content.main_menu_buttons.units_converter.temperature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_RedDarkUltra
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_RedLightExtra

@Composable
fun TemperatureConverterDialog(isTimeDialogOpen: MutableState<Boolean>) {

    ShowAlertDialog(
        isDialogOpen = isTimeDialogOpen,
        defaultBottomBar = true,
        dialogContent = { TemperatureConverterDialogContent() },
        dialogColor = RB_RedDarkUltra,
        title = "Temperature Converter",
        titleColor = RB_RedLightExtra,
        titleSize = 20.sp,
        titleWeight = FontWeight.Bold
    )

}

