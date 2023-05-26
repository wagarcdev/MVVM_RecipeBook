package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_title_and_time_row.EditRecipeNameButtonDisplay
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_title_and_time_row.EditRecipeTimeButtonDisplay
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Red
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.GradientColumn
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ImageSelectorAlertDialogContent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageTitleAndTimeSelectorRowItem(
    catalogViewModel: CatalogViewModel,
    itemPadding: Dp = 1.dp
) {
    /**
     * TODO
     *
     * Take image filepath from Gallery and Camera Capture to pass to Object Recipe for Saving
     *
     * Add systemBarPadding to buttons on ImageRow
     * */

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    var isCameraSelected = false
//    var imageUri: Uri?
    var bitmap: Bitmap?
    var photoFilepath: String

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

        bitmap =

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        context.contentResolver,
                        uri!!
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }

        photoFilepath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null)

        coroutineScope.launch {
            catalogViewModel.currentRecipe?.let {
                it.imageFilepath = photoFilepath
                catalogViewModel.updateRecipe(it)
            }
        }

    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { btm: Bitmap? ->

        photoFilepath = MediaStore.Images.Media.insertImage(context.contentResolver, btm, "Title", null)

        coroutineScope.launch {
            catalogViewModel.currentRecipe?.let {
                it.imageFilepath = photoFilepath
                catalogViewModel.updateRecipe(it)
            }
        }

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



    val height = 240.dp


    Surface(
        modifier = Modifier
            .padding(0.dp)
            .height(height),
        color = RB_Transparent
    ) {



        Row(
            modifier = Modifier
                .padding(0.dp)
                .height(height)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier

                    .padding(0.dp)
                    .height(height)
                    .fillMaxWidth(),
                elevation = 5.dp,
                shape = RectangleShape,
                border = BorderStroke(0.dp, Color(0x00000000)),
                backgroundColor = RB_Black
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(catalogViewModel.currentRecipe?.imageFilepath)
                        .crossfade(true)
                        .fallback(R.drawable.no_image)
                        .build(),
                    contentDescription = "",
                    contentScale =
                    if (catalogViewModel.currentRecipe?.imageFilepath != null) {
                        ContentScale.FillWidth
                    } else {
                        ContentScale.Fit
                    }
                )
            }
        }

        GradientColumn(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth(1f),
            columnHorizontalAlignment = Alignment.CenterHorizontally,
            columnVerticalArrangement = Arrangement.Bottom ,
            topSpacerTransition = false,
            bottomSpacerTransition = false,
            columnTopColor = Color(0x00000000),
            columnMiddleColor = Color(0x33000000),
            columnBottomColor = Color(0xB3000000),
            contentHorizontalAlignment = Alignment.CenterHorizontally,
            contentVerticalArrangement =  Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {

                val isDialogOpen = remember { mutableStateOf(false) }

                val coroutineScope = rememberCoroutineScope()



                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    EditRecipeTimeButtonDisplay(catalogViewModel, coroutineScope)

                    Spacer(modifier = Modifier.height(8.dp))

                    EditRecipeNameButtonDisplay(catalogViewModel, itemPadding, coroutineScope)

                }



                Column(
                    modifier = Modifier
                        .height(height),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    if (catalogViewModel.currentRecipe != null) {
                        AnimatedVisibility(
                            visible = (catalogViewModel.currentRecipe!!.imageFilepath != null),
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
                                    color = RB_Red
                                ),
                                onClick = {
                                    coroutineScope.launch(Dispatchers.Default) {
                                        catalogViewModel.currentRecipe!!.imageFilepath = null
                                        catalogViewModel.updateRecipe(catalogViewModel.currentRecipe!!)
                                    }
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
                                        tint = RB_Red


                                    )
                                }
                            }
                            /** DELETE Image Button*/
                        }
                    }
                }


                Column(
                    modifier = Modifier
                        .height(height),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {


                    /** CHANGE Image Button*/
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(60.dp),
                        backgroundColor = RB_Transparent,
                        shape = RoundedCornerShape(50.dp),
                        border = BorderStroke(
                            2.dp,
                            color = MaterialTheme.colors.primary
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
                            if (catalogViewModel.currentRecipe != null) {
                                Icon(
                                    modifier = Modifier
                                        .size(35.dp),
                                    painter =
                                    painterResource(
                                        if (catalogViewModel.currentRecipe?.imageFilepath == null) {
                                            R.drawable.ic_add_image
                                        } else {
                                            R.drawable.ic_change_arrows
                                        }
                                    ),
                                    contentDescription = "Change Image Button",
                                    tint = RB_White
                                )
                            }

                        }
                    }
                    /** CHANGE Image Button*/
                }


                /** NOT IN LAYOUT ARRANGEMENT */
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
                /** NOT IN LAYOUT ARRANGEMENT */


            }

        }


    }


}

