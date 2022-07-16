package com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.main_menu_buttons.units_converter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.main_menu_buttons.units_converter.temperature.TemperatureConverterDialog
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.main_menu_buttons.units_converter.time.TimeConverterDialog
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.main_menu_buttons.units_converter.volume.VolumeConverterDialog
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.main_menu_buttons.units_converter.weight.WeightConverterDialog
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.SelectOptionAlertDialogButton
import com.arcieri.wagner.mvvm_recipebook.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun UnitConverterDialogContent() {

    val coroutineScope = rememberCoroutineScope()

    val cardBorderStroke = 3.dp
    val cardColor = RB_Black_75
    val cardHeight = 135.dp
    val cardWidth = 135.dp
    val iconSize = 60.dp

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            /**   Volume   */

            val isVolumeDialogOpen = remember { mutableStateOf(false) }

            SelectOptionAlertDialogButton(
                cardHeight = cardHeight,
                cardWidth = cardWidth,
                cardColor = cardColor,
                cardBorderStroke = BorderStroke(cardBorderStroke, color = RB_IndigoDark),
                textColor = RB_White,
                iconColor = RB_White,
                iconID = R.drawable.liquids_measurement,
                iconSize = iconSize,
                contentDesc = "volume",
                buttonText = "Volume",
                onClick = { coroutineScope.launch { isVolumeDialogOpen.value = true  } }
            )

            VolumeConverterDialog(isVolumeDialogOpen)



            Spacer(modifier = Modifier.padding(5.dp))

            /**   Weight   */

            val isWeightDialogOpen = remember { mutableStateOf(false) }

            SelectOptionAlertDialogButton(
                cardHeight = cardHeight,
                cardWidth = cardWidth,
                cardColor = cardColor,
                cardBorderStroke = BorderStroke(cardBorderStroke, color = RB_YellowDark),
                textColor = RB_White,
                iconColor = RB_White,
                iconID = R.drawable.ic_weight,
                iconSize = iconSize,
                contentDesc = "weight",
                buttonText = "Weight",
                onClick = { coroutineScope.launch { isWeightDialogOpen.value = true } }
            )

            WeightConverterDialog(isWeightDialogOpen)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            /**   Time   */

            val isTimeDialogOpen = remember { mutableStateOf(false) }

            SelectOptionAlertDialogButton(
                cardHeight = cardHeight,
                cardWidth = cardWidth,
                cardColor = cardColor,
                cardBorderStroke = BorderStroke(cardBorderStroke, color = RB_OrangeDark),
                textColor = RB_White,
                iconColor = RB_White,
                iconID = R.drawable.fast_time_flat,
                iconSize = iconSize,
                contentDesc = "time",
                buttonText = "Time",
                onClick = { coroutineScope.launch { isTimeDialogOpen.value = true } }
            )
            TimeConverterDialog(isTimeDialogOpen)



            Spacer(modifier = Modifier.padding(5.dp))




            /**   Temperature   */

            val isTemperatureDialogOpen = remember { mutableStateOf(false) }

            SelectOptionAlertDialogButton(
                cardHeight = cardHeight,
                cardWidth = cardWidth,
                cardColor = cardColor,
                cardBorderStroke = BorderStroke(cardBorderStroke, color = RB_RedDark),
                textColor = RB_White,
                iconColor = RB_White,
                iconID = R.drawable.termometer,
                iconSize = iconSize,
                contentDesc = "temperature",
                buttonText = "Temperature",
                onClick = { coroutineScope.launch { isTemperatureDialogOpen.value = true } }
            )

            TemperatureConverterDialog(isTemperatureDialogOpen)
        }

    }
}

@Preview
@Composable
fun UnitConverterDialogContentPreview() {

    UnitConverterDialogContent()


}


