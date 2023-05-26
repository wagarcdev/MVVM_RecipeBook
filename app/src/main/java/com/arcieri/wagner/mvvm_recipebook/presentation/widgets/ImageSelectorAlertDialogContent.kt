package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.arcieri.wagner.mvvm_recipebook.R

@Composable
fun ImageSelectorAlertDialogContent(
    context: Context,
    galleryLauncher: ManagedActivityResultLauncher<String, Uri?>,
    isCameraSelected: Boolean,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    isDialogOpen: MutableState<Boolean>,
    cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?>
) {
    var isCameraSelected1 = isCameraSelected
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        SelectOptionAlertDialogButton(
            cardHeight = 150.dp,
            cardWidth = 130.dp,
            iconID = R.drawable.ic_add_image,
            iconSize = 62.dp,
            contentDesc = "Add Image From Gallery Icon",
            buttonText = "From Gallery",
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context, Manifest.permission.READ_EXTERNAL_STORAGE
                    ) -> {
                        galleryLauncher.launch("image/*")

                    }
                    else -> {
                        isCameraSelected1 = false
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
                isDialogOpen.value = false
            }
        )

        Spacer(modifier = Modifier.padding(5.dp))

        SelectOptionAlertDialogButton(
            cardHeight = 150.dp,
            cardWidth = 130.dp,
            iconID = R.drawable.ic_add_photo,
            iconSize = 60.dp,
            contentDesc = "Take Photo Icon",
            buttonText = "Take Photo",
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context, Manifest.permission.CAMERA
                    ) -> {
                        cameraLauncher.launch()

                    }
                    else -> {
                        isCameraSelected1 = true
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
                isDialogOpen.value = false
            }
        )


    }
}