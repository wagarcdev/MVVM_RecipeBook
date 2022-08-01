package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.SelectOptionAlertDialogButton
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageSelector(recipeDraft: Recipe) {



    val recipeDraftImage = remember { mutableStateOf(recipeDraft.imageFilepath) }

    val context = LocalContext.current

    var isCameraSelected = false
    var imageUri: Uri?
    var bitmap: Bitmap?
    var displayBitmap: Bitmap? = null

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        bitmap = null
        displayBitmap =

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        context.contentResolver,
                        imageUri!!
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { btm: Bitmap? ->
        bitmap = btm
        imageUri = null
        displayBitmap = bitmap
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            if (isCameraSelected) {
                cameraLauncher.launch()
            } else {
                galleryLauncher.launch("image/*")
            }
        } else {
            Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
        }
    }


    Surface {


        Row(
            modifier = Modifier
                .padding(0.dp)
                .height(170.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(0.dp)
                    .height(170.dp)
                    .fillMaxWidth(),
                elevation = 5.dp,
                border = BorderStroke(0.dp, Color(0x00000000))
            ) {

                AsyncImage(
                    model = displayBitmap ?: recipeDraftImage.value ?:
                    if (recipeDraftImage.value != "") {
                        ImageRequest.Builder(LocalContext.current)
                            .data(recipeDraftImage.value)
                            .crossfade(true)
                            .fallback(R.drawable.no_image)
                            .build()

                    } else {
                        R.drawable.no_image
                    }, contentDescription = "",
                    contentScale =
                    if (recipeDraftImage.value == "" || recipeDraftImage.value == null) {
                        ContentScale.Fit
                    } else {
                        ContentScale.FillWidth
                    }

                )


            }
        }


        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 0.dp)
                .height(170.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                val isDialogOpen = remember { mutableStateOf(false) }

                ShowAlertDialog(
                    isDialogOpen = isDialogOpen,
                    dialogContent = {
                        ImageSelectorAlertDialogContent(
                            context,
                            galleryLauncher,
                            isCameraSelected,
                            permissionLauncher,
                            isDialogOpen,
                            cameraLauncher
                        )
                    },
                    defaultBottomBar = false,
                )

                Column(
                    modifier = Modifier
                        .height(170.dp)
                        .fillMaxWidth(0.3f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    AnimatedVisibility(
                        visible = (recipeDraftImage.value != null),
                        enter = EnterTransition.None,
                        exit = ExitTransition.None
                    ) {

                        val coroutineScope = rememberCoroutineScope()


                        /** DELETE Image Button*/
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(60.dp),
                            backgroundColor = Color(0x1A000000),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0xFFFF2929)
                            ),
                            onClick = {
                                coroutineScope.launch(Dispatchers.Default) {
                                    recipeDraftImage.value = ""
                                }

//                                    recipeDraft.image = null

                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(35.dp),
                                    imageVector = Icons.Rounded.Delete,
                                    contentDescription = "Delete Image Button",
                                    tint = Color(0xFFFF2929)


                                )
                            }
                        }
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/
                        /** DELETE Image Button*/

                    }
                }


                Column(
                    modifier = Modifier
                        .height(170.dp)
                        .fillMaxWidth(1f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/

                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/

                    /** CHANGE Image Button*/
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(60.dp),
                        backgroundColor =
                        if (recipeDraftImage.value == null) {
                            Color(0x00000000)
                        } else {
                            Color(0x1A000000)
                        },
                        shape = RoundedCornerShape(50.dp),
                        border = BorderStroke(
                            2.dp,
                            color =
                            if (recipeDraftImage.value == null) {
                                Color(0xFF002F9C)
                            } else {
                                Color(0xFFFCFCFC)
                            }
                        ),
                        onClick = { isDialogOpen.value = true }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(35.dp),
                                painter =
                                painterResource(
                                    if (recipeDraftImage.value == null) {
                                        R.drawable.ic_add_image
                                    } else {
                                        R.drawable.ic_change_arrows
                                    }
                                ),
                                contentDescription = "Change Image Button",
                                tint =
                                if (recipeDraftImage.value == null) {
                                    Color(0xFF002F9C)
                                } else {
                                    Color(0xFFFCFCFC)
                                }
                            )

                        }
                    }
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/
                    /** CHANGE Image Button*/


                }


            }

        }


    }


}

@Composable
private fun ImageSelectorAlertDialogContent(
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