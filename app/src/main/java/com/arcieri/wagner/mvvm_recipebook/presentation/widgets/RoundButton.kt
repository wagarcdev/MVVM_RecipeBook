package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent


/**
 * RoundButton
 *
 * Customizable Round Button with Border based on a Card and and Icon
 *
 * This Button use an Int ID for the Icon,
 * please consider creating a new vector image in resources drawables for it.
 *
 * @param padding Unit in Dp of Modifier .padding of the Button. Default = 1.dp
 * @param elevation Unit in Dp of Modifier .elevation of the Button. Default = 2.dp
 * @param buttonSize Unit in Dp of Modifier .size of the Button. Default = 30.dp
 * @param backgroundColor background Color of the Button. Default = Color(0x1A000000)(Black at 10% alpha)
 * @param borderStroke Unit in Dp for border of the Button. Default = 1.dp
 * @param borderColor Color of the Button border. Default = Color(0xFF0000FF)
 * @param iconID Int with the ID of the the Icon in Resources, Default = R.drawable.ic_launcher_foreground
 * @param iconSize Unit in Dp for the Size of the Icon inside the Button. Default = 20.dp
 * @param iconDescription String with the Icon/Button description. Default = ""
 * @param iconColor Color of the Icon inside of the Button. Default = Color(0xFF0000FF)
 * @param onClick Method invoked on the Button Click listener. Default = { }
 *
 * @author Wagner Arcieri
 * @version 1.0.0 - 30/05/2022 - Initial Release
 * */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundButton(
    padding: Dp = 1.dp,
    elevation: Dp = 0.dp,
    buttonSize: Dp = 30.dp,
    leftGradientColor: Color = Color(0xFF000000),
    centerLeftGradientColor: Color = Color(0xFF1B1B1B),
    centerGradientColor: Color = Color(0xFF1B1B1B),
    centerRightGradientColor: Color = Color(0xFF1B1B1B),
    rightGradientColor: Color = Color(0xFF3F3F3F),
    borderStroke: BorderStroke = BorderStroke(0.dp, RB_Transparent),
    borderColor: Color = Color(0xFF0000FF),
    iconID: Int = R.drawable.ic_launcher_foreground,
    iconSize: Dp = 20.dp,
    iconDescription: String = "",
    iconColor: Color = Color(0xFF0000FF),
    onClick: () -> Unit = { }
) {

    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick.invoke() }
            .clip(CircleShape)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        leftGradientColor,
                        centerLeftGradientColor,
                        centerGradientColor,
                        centerRightGradientColor,
                        rightGradientColor,
                    ),
                )
            ),
        elevation = 0.dp,
        border = borderStroke,
        backgroundColor = Color(0x00000000)

    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(iconSize),
                    painter = painterResource(id = iconID),
                    contentDescription = iconDescription,
                    tint = iconColor


                )

            }
        }
    }




//    Card(
//        modifier = Modifier
//            .padding(padding)
//            .size(buttonSize)
//            .clip(CircleShape)
//            .background(
//                Brush.horizontalGradient(
//                    listOf(
//                        leftGradientColor,
//                        centerLeftGradientColor,
//                        centerGradientColor,
//                        centerRightGradientColor,
//                        rightGradientColor,
//                    ),
//                )
//            ),
//        shape = RoundedCornerShape(buttonSize),
//        elevation = elevation,
//        border = BorderStroke(
//            width = borderStroke,
//            color = borderColor
//        ),
//        onClick = { onClick.invoke() }
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                modifier = Modifier
//                    .size(iconSize),
//                painter = painterResource(id = iconID),
//                contentDescription = iconDescription,
//                tint = iconColor
//
//
//            )
//        }
//    }
}


/**
 *  Button is nested in a Column for centralization
 *  and is over a colored Surface for better Preview
*/
@Preview(showBackground = true)
@Composable
fun RoundButtonPreview() {


    
    Surface(modifier = Modifier.size(50.dp), color = Color(0xFFFFFFFF)) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundButton()
        }
        

        
    }
    
}