package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.units_converter.volume

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_IndigoDarkUltra
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_IndigoLightExtra

@Composable
fun VolumeConverterDialog(isVolumeOpen: MutableState<Boolean>) {

    ShowAlertDialog(
        isDialogOpen = isVolumeOpen,
        defaultBottomBar = true,
        dialogContent = { VolumeConverterDialogContent() },
        dialogColor = RB_IndigoDarkUltra,
        title = "Volume Converter",
        titleColor = RB_IndigoLightExtra,
        titleSize = 20.sp,
        titleWeight = FontWeight.Bold
    )



}

