package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.units_converter.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.units_converter.components.ConverterRow
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_YellowDarkDeep
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_YellowLight

@Composable
fun WeightConverterDialogContent() {



    val descriptionTextColor = RB_YellowLight
    val dividerColor = RB_YellowDarkDeep


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /** Units */
        /** Units */

        //Milligram
        ConverterRow(
            iconID = R.drawable.ic_spoon_tea,
            iconDescription = "Milligram",
            unit = "mg",
            description = "Milligram",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Grams
        ConverterRow(
            iconID = R.drawable.ic_milliliters,
            iconDescription = "Gram",
            description = "Gram",
            unit = "g",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Kilograms
        ConverterRow(
            iconID = R.drawable.ic_liter,
            iconDescription = "Kilogram",
            description = "Kilogram",
            unit = "Kg",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )


        //Ounce
        ConverterRow(
            iconID = R.drawable.ic_spoon_dessert,
            iconDescription = "Ounce",
            description = "Ounce",
            unit = "Oz",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Pound
        ConverterRow(
            iconID = R.drawable.fluid_ounce,
            iconDescription = "Pound",
            description = "Pound",
            unit = "Lb",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )




    }

}