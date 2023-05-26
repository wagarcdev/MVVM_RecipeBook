package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    enabled: Boolean = true,
    isLoading: Boolean = false
) = Button(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(
        containerColor = Color.White,
        contentColor = contentColorFor(backgroundColor = Color.White)
    )
) {
    val displayText =
        if (isLoading) stringResource(id = R.string.loading)
        else text

    Icon(
        painter = painterResource(id = R.drawable.ic_google_logo),
        contentDescription = null,
        tint = Color.Unspecified,
        modifier = Modifier.size(22.dp)

    )

    Spacer(modifier = Modifier.width(width = 16.dp))

    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        text = displayText,
        style = textStyle
    )
}