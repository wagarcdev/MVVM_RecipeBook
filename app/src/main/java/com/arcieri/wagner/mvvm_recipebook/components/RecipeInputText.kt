package com.arcieri.wagner.mvvm_recipebook.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
    textAlignment: TextAlign = TextAlign.Start,
    textFontSize: TextUnit = 10.sp,
    labelFontSize: TextUnit = 10.sp,
    onImeAction: () -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x00FFFFFF)),
        maxLines = maxLines,
        label = {
            if (label != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = label,
                    fontSize = labelFontSize,
                    textAlign = textAlignment,
                    maxLines = 1
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier,
        textStyle = TextStyle(textAlign = textAlignment, fontSize = textFontSize),
        readOnly = false
    )


}