package com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.main_menu_buttons.units_converter.time

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.main_menu_buttons.units_converter.components.ConverterRow
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_OrangeDarkDeep
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_OrangeLight

@Composable
fun TimeConverterDialogContent() {

    val descriptionTextColor = RB_OrangeLight
    val dividerColor = RB_OrangeDarkDeep


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        /** Units */

        //Seconds
        ConverterRow(
            iconID = R.drawable.ic_spoon_tea,
            iconDescription = "Seconds",
            unit = "sec",
            description = "Seconds",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Minutes
        ConverterRow(
            iconID = R.drawable.ic_milliliters,
            iconDescription = "Minutes",
            description = "Minutes",
            unit = "min",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Hours
        ConverterRow(
            iconID = R.drawable.ic_liter,
            iconDescription = "Hour",
            description = "Hour",
            unit = "H",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )


        //Days
        ConverterRow(
            iconID = R.drawable.ic_spoon_dessert,
            iconDescription = "Days",
            description = "Days",
            unit = "d",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Weeks
        ConverterRow(
            iconID = R.drawable.fluid_ounce,
            iconDescription = "Weeks",
            description = "Weeks",
            unit = "w",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Months
        ConverterRow(
            iconID = R.drawable.fluid_ounce,
            iconDescription = "Months",
            description = "Months",
            unit = "m",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )

        //Years
        ConverterRow(
            iconID = R.drawable.fluid_ounce,
            iconDescription = "Years",
            description = "Years",
            unit = "y",
            textAlign = TextAlign.Start,
            descFontColor = descriptionTextColor,
            dividerColor = dividerColor
        )




    }


}