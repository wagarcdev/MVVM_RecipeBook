package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.MainMenuButton
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.UnitsConverterAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import kotlinx.coroutines.launch


@Composable
fun MainMenuButtons(
    navHostController: NavHostController
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val textAlign = TextAlign.Start
    val textFontSize = 20.sp
    val buttonsMinHeight = 80.dp
    val iconSize = 45.dp
    val iconWeight = 0.18f
    val buttonCornerDp = 15.dp
    val edgesSpacersHeight = 36.dp
    val inBetweenSpacersHeight = 12.dp

    Column(
        modifier = Modifier
            .imePadding()
            .defaultMinSize(300.dp)
            .wrapContentHeight()
            .fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {

        Spacer(modifier = Modifier.height(edgesSpacersHeight))

        /**   Assemble Menu     */
        MainMenuButton(
            iconID = R.drawable.ic_assemble_menu_flat_white,
            iconDescription = "Assemble Menu Button",
            iconSize = iconSize,
            iconWeight = iconWeight,
            buttonMinHeight = buttonsMinHeight,
            cornerDp = buttonCornerDp,
            text = "Assemble Menu",
            textAlign  = textAlign,
            textFontSize = textFontSize,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor = RB_GreenDarker,
            centerLeftGradientColor = RB_GreenDark,
            centerGradientColor = RB_GreenDark,
            centerRightGradientColor = RB_Green,
            rightGradientColor = RB_GreenLight,
            textFontColor = RB_White
        ) {
        }

        Spacer(modifier = Modifier.height(inBetweenSpacersHeight))

        /**   Unit Converter     */
        val isUnitConverterOpen = remember { mutableStateOf(false) }

        MainMenuButton(
            iconID = R.drawable.balance_flat_white_icon,
            iconDescription = "Units Converter",
            iconSize = (iconSize),
            iconWeight = iconWeight,
            buttonMinHeight = buttonsMinHeight,
            cornerDp = buttonCornerDp,
            text = "Units Converter",
            textAlign  = textAlign,
            textFontSize = textFontSize,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor = RB_YellowDarker,
            centerLeftGradientColor = RB_YellowDark,
            centerGradientColor = RB_YellowDark,
            centerRightGradientColor = RB_Yellow,
            rightGradientColor = RB_YellowLight,
            textFontColor = RB_White
        ) { coroutineScope.launch { isUnitConverterOpen.value = true } }

        Spacer(modifier = Modifier.height(inBetweenSpacersHeight))

        /**   Recipes     */
        MainMenuButton(
            iconID = R.drawable.recipe_book_white_flat,
            iconDescription = "Recipes Button Icon",
            iconSize = iconSize,
            iconWeight = iconWeight,
            buttonMinHeight = buttonsMinHeight,
            cornerDp = buttonCornerDp,
            text = "Recipes",
            textAlign  = textAlign,
            textFontSize = textFontSize,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor =  RB_OrangeDarker,
            centerLeftGradientColor = RB_OrangeDark,
            centerGradientColor = RB_OrangeDark,
            centerRightGradientColor = RB_Orange,
            rightGradientColor = RB_OrangeLight,
            textFontColor = RB_White
        ) {

            navHostController.navigate(route = Screens.MainScreen.name)

            /**New Recipe Code*/
//            val recipeId = catalogViewModel.newRecipe()
//
//            catalogViewModel.navHostController.navigate(route = Screens.AddEditRecipeScreen.name+"/$recipeId")

        }

        Spacer(modifier = Modifier.height(edgesSpacersHeight))

        /**Not in LAYOUT*/
        UnitsConverterAlertDialog(isUnitConverterOpen)
        /**Not in LAYOUT*/

    }

}





