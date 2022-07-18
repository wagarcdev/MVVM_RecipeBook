package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.units_converter.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_OrangeDarkUltra
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_OrangeLightExtra

@Composable
fun TimeConverterDialog(isTimeDialogOpen: MutableState<Boolean>) {

    ShowAlertDialog(
        isDialogOpen = isTimeDialogOpen,
        defaultBottomBar = true,
        dialogContent = { TimeConverterDialogContent() },
        dialogColor = RB_OrangeDarkUltra,
        title = "Time Converter",
        titleColor = RB_OrangeLightExtra,
        titleSize = 20.sp,
        titleWeight = FontWeight.Bold
    )

}

