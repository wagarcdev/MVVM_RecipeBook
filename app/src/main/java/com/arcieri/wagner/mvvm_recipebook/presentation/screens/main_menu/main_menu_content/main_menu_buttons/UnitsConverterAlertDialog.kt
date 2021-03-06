package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.units_converter.UnitConverterDialogContent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_75

@Composable
fun UnitsConverterAlertDialog(isDialogOpen: MutableState<Boolean>) {
    ShowAlertDialog(
        isDialogOpen = isDialogOpen,
        defaultBottomBar = true,
        dialogContent = { UnitConverterDialogContent() },
        dialogColor = RB_Black_75,
        dialogShape = RoundedCornerShape(50.dp)
    )
}