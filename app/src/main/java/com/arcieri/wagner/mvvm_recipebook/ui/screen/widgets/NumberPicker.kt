package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R

@Composable
fun NumberPickerView() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.layoutId("picker"),
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "Horizontal Number Picker",
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .align(CenterHorizontally)
        )

        HorizontalNumberPicker(
            min = 10,
            max = 100,
            default = 50,
            onValueChange = { value ->
                Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Vertical Number Picker",
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .align(CenterHorizontally)
        )

        VerticalNumberPicker(
            min = 20,
            max = 30,
            default = 20,
            onValueChange = { value ->
                Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun VerticalNumberPicker(
    modifier: Modifier = Modifier,
    width: Dp = 45.dp,
    min: Int = 0,
    max: Int = 10,
    default: Int = min,
    onValueChange: (Int) -> Unit = {}
) {
    val number = remember { mutableStateOf(default) }

    Column {
        PickerButton(
            size = width,
            drawable = R.drawable.ic_arrow_up,
            enabled = number.value < max,
            onClick = {
                if (number.value < max) number.value++
                onValueChange(number.value)
            }
        )

        Text(
            text = number.value.toString(),
            fontSize = (width.value / 2).sp,
            modifier = Modifier
                .padding(10.dp)
                .width(IntrinsicSize.Max)
                .align(CenterHorizontally)
        )

        PickerButton(
            size = width,
            drawable = R.drawable.ic_arrow_down,
            enabled = number.value > min,
            onClick = {
                if (number.value > min) number.value--
                onValueChange(number.value)
            }
        )
    }
}

@Composable
fun HorizontalNumberPicker(
    modifier: Modifier = Modifier,
    height: Dp = 45.dp,
    min: Int = 0,
    max: Int = 10,
    default: Int = min,
    onValueChange: (Int) -> Unit = {}
) {
    val number = remember { mutableStateOf(default) }

    Row {
        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_left,
            enabled = number.value > min,
            onClick = {
                if (number.value > min) number.value--
                onValueChange(number.value)
            }
        )

        Text(
            text = number.value.toString(),
            fontSize = (height.value / 2).sp,
            modifier = Modifier
                .padding(10.dp)
                .height(IntrinsicSize.Max)
                .align(CenterVertically)
        )

        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_right,
            enabled = number.value < max,
            onClick = {
                if (number.value < max) number.value++
                onValueChange(number.value)
            }
        )
    }
}

@Composable
fun PickerButton(
    size: Dp = 45.dp,
    @DrawableRes drawable: Int = R.drawable.ic_arrow_left,
    backgroundColor: Color = Color(0xFF888888),
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val contentDesc = LocalContext.current.resources.getResourceName(drawable)


    Image(
        painter = painterResource(id = drawable),
        contentDescription = contentDesc,
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor, CircleShape)
            .clip(CircleShape)
            .size(size = size)
            .clickable(
                enabled = enabled,
                onClick = { onClick() }
            )
    )
}


@Preview
@Composable
fun NumberPickerPreview() {

    NumberPickerView()

}

