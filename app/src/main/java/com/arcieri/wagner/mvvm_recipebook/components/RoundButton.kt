package com.arcieri.wagner.mvvm_recipebook.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundButton(
    padding: Dp = 1.dp,
    elevation: Dp = 0.dp,
    buttonSize: Dp = 30.dp,
    onClick: () -> Unit,
    backgroundColor: Color = Color(0x1A000000),
    borderStroke: Dp = 1.dp,
    borderColor: Color = Color(0xFF0000FF),
    iconID: Int = R.drawable.ic_fork_knife,
    iconSize: Dp = 20.dp,
    iconDescription: String = "",
    iconColor: Color = Color(0xFF0000FF)
) {
    Card(
        modifier = Modifier
            .padding(padding)
            .size(buttonSize),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(buttonSize),
        elevation = elevation,
        border = BorderStroke(
            width = borderStroke,
            color = borderColor
        ),
        onClick = { onClick.invoke() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(iconSize),
                painter = painterResource(id = iconID),
                contentDescription = iconDescription,
                tint = iconColor


            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoundButtonPreview() {
    
    Surface(modifier = Modifier.size(50.dp), color = Color(0xFF204020)) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundButton(
                onClick = { /*TODO*/ },
                elevation = 0.dp
            )
        }
        

        
    }
    
}