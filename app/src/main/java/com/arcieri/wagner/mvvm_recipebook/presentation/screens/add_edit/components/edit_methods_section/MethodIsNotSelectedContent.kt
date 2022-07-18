package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_methods_section

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.IndexedMethod

@Composable
fun MethodIsNotSelectedContent(
    itemPadding: Dp,
    index: Int,
    method: String
) {
    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = 70.dp)
            .fillMaxWidth()
            .padding(itemPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(itemPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IndexedMethod(index, method)
        }


    }
}