package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.units_converter.temperature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.units_converter.components.ConverterRow
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_RedDarkDeep
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_RedLight

@Composable
fun TemperatureConverterDialogContent() {

    val descriptionTextColor = RB_RedLight
    val dividerColor = RB_RedDarkDeep


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Celsius
        ConverterRow(
            iconID = R.drawable.ic_spoon_tea,
            iconDescription = "Celsius",
            unit = "ºC",
            description = "Celsius",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Fahrenheit
        ConverterRow(
            iconID = R.drawable.ic_milliliters,
            iconDescription = "Fahrenheit",
            description = "Fahrenheit",
            unit = "ºF",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Kelvin
        ConverterRow(
            iconID = R.drawable.ic_liter,
            iconDescription = "Fahrenheit",
            description = "Fahrenheit",
            unit = "ºK",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )
    }
}