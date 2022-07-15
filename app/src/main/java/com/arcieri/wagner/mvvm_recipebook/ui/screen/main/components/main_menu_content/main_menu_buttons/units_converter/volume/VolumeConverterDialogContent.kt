package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.units_converter.volume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.units_converter.components.ConverterRow
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_IndigoDarkDeep
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_IndigoLight

@Composable
fun VolumeConverterDialogContent() {


    val descriptionTextColor = RB_IndigoLight
    val dividerColor = RB_IndigoDarkDeep


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /** Units */

        //Milliliters
        ConverterRow(
            iconID = R.drawable.ic_milliliters,
            iconDescription = "Milliliters",
            description = "Milliliters",
            unit = "ml",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Liters
        ConverterRow(
            iconID = R.drawable.ic_liter,
            iconDescription = "Liters",
            description = "Liters",
            unit = "L",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )



        //Fluid ounce
        ConverterRow(
            iconID = R.drawable.fluid_ounce,
            iconDescription = "Fluid ounce",
            description = "Fluid Ounce",
            unit = "fl. oz.",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )



        /** Spoons */

        //Tea Spoon
        ConverterRow(
            iconID = R.drawable.ic_spoon_tea,
            iconDescription = "Tea Spoon",
            unit = "tsp",
            description = "Teaspoon",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Dessert Spoon
        ConverterRow(
            iconID = R.drawable.ic_spoon_dessert,
            iconDescription = "Dessert-Spoon",
            description = "Dessert-Spoon",
            unit = "dsp",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Table Spoon
        ConverterRow(
            iconID = R.drawable.ic_spoon_table,
            iconDescription = "Tablespoon",
            description = "Tablespoon",
            unit = "tbsp",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        /** Recipients */

        //Cup

        ConverterRow(
            iconID = R.drawable.ic_cup,
            iconDescription = "Cup",
            description = "Cup",
            unit = "c",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )


        //Pint
        ConverterRow(
            iconID = R.drawable.ic_pint,
            iconDescription = "Pint",
            description = "Pint",
            unit = "pt",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Gallon
        ConverterRow(
            iconID = R.drawable.ic_gallon,
            iconDescription = "Gallon",
            description = "Gallon",
            unit = "gal",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor,
            hasDivider = false
        )
    }
}