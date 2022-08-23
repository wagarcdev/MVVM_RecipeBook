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
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.di.AppModule
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInGoogleViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.MainMenuButton
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.UnitsConverterAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import kotlinx.coroutines.launch


@Composable
fun MainMenuButtons(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInGoogleViewModel,
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val textAlign = TextAlign.Center

    Column(
        modifier = Modifier
            .imePadding()
            .defaultMinSize(300.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        /**   Assemble Menu     */
        MainMenuButton(
            iconID = R.drawable.ic_assemble_menu_flat_white,
            iconDescription = "Assemble Menu Button",
            text = "Assemble Menu",
            textAlign = textAlign,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor =  RB_VioletDarker,
            centerLeftGradientColor = RB_VioletDark,
            centerGradientColor = RB_VioletDark,
            centerRightGradientColor = RB_Violet,
            rightGradientColor = RB_VioletLight,
            fontColor = RB_White
        ) {
            catalogViewModel.navHostController.navigate(route = Screens.TestScreen.name)

        }

        /**   Search on the Web     */
        MainMenuButton(
            iconID = R.drawable.web_flat_white_icon,
            iconDescription = "Web Search Button",
            text = "Web Search",
            textAlign = textAlign,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor =  RB_IndigoDarker,
            centerLeftGradientColor = RB_IndigoDark,
            centerGradientColor = RB_IndigoDark,
            centerRightGradientColor = RB_Indigo,
            rightGradientColor = RB_IndigoLight,
            fontColor = RB_White
        ) {
//            TODO make API / DTOs to import recipes
        }

        /**   Recipes   */
        MainMenuButton(
            iconID = R.drawable.recipe_book_white_flat,
            iconDescription = "Recipes Icon",
            text = "Recipes",
            textAlign = textAlign,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor =  RB_BlueDarker,
            centerLeftGradientColor = RB_BlueDark,
            centerGradientColor = RB_BlueDark,
            centerRightGradientColor = RB_Blue,
            rightGradientColor = RB_BlueLight
        ) {
            catalogViewModel.navHostController.navigate(route = Screens.CatalogScreen.name)
        }

        /**   NEW Recipe   */
        MainMenuButton(
            iconID = R.drawable.new_recipe_icon_flat_white,
            iconDescription = "Create Recipe Button",
            text = "Create Recipe",
            textAlign = textAlign,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor = RB_GreenDarker,
            centerLeftGradientColor = RB_GreenDark,
            centerGradientColor = RB_GreenDark,
            centerRightGradientColor = RB_Green,
            rightGradientColor = RB_GreenLight,
            fontColor = RB_White
        ) {
            val recipeId = catalogViewModel.newRecipe()

            catalogViewModel.navHostController.navigate(route = Screens.AddEditRecipeScreen.name+"/$recipeId")
        }

        var db = AppModule.providesAppDatabase(context).isOpen
        /**   Import Recipe     */
        MainMenuButton(
            iconID = R.drawable.ic_import_flat_white,
            iconSize = 23.dp,
            iconDescription = "Import Recipe",
            text = "Import Recipe",
            textAlign = textAlign,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor =  RB_YellowDarker,
            centerLeftGradientColor = RB_YellowDark,
            centerGradientColor = RB_YellowDark,
            centerRightGradientColor = RB_Yellow,
            rightGradientColor = RB_YellowLight,
            fontColor = RB_White
        ) {
        }


        /**   Unit Converter     */

        val isUnitConverterOpen = remember { mutableStateOf(false) }

        MainMenuButton(
            iconID = R.drawable.balance_flat_white_icon,
            iconDescription = "Units Converter",
            text = "Units Converter",
            textAlign = textAlign,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor =  RB_OrangeDarker,
            centerLeftGradientColor = RB_OrangeDark,
            centerGradientColor = RB_OrangeDark,
            centerRightGradientColor = RB_Orange,
            rightGradientColor = RB_OrangeLight,
            fontColor = RB_White
        ) { coroutineScope.launch { isUnitConverterOpen.value = true } }

        UnitsConverterAlertDialog(isUnitConverterOpen)


        /**   Sign Out     */
        MainMenuButton(
            iconID = R.drawable.ic_sign_out,
            iconDescription = "Sign Out",
            text = "Sign Out",
            textAlign = textAlign,
            textFillMaxWidthFloat = 0.85f,
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor =  RB_RedDarker,
            centerLeftGradientColor = RB_RedDark,
            centerGradientColor = RB_RedDark,
            centerRightGradientColor = RB_Red,
            rightGradientColor = RB_RedLight,
            fontColor = RB_White
        ) {
            coroutineScope.launch {
                signInGoogleViewModel.signOut(context)
            }
        }
    }

}

