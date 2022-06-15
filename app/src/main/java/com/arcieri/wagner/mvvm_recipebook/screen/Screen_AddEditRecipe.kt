package com.arcieri.wagner.mvvm_recipebook.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.components.*
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.floor


@Composable
fun ScreenAddEditRecipe(
    recipeBookViewModel: RecipeBookViewModel,
    recipeName: String?
) {

    val recipeDraft: Recipe =
        if (recipeName != "NEW") {
            recipeBookViewModel.recipeList.collectAsState().value.filter { recipe -> recipe.name == recipeName  }[0]
        } else {
            Recipe(name = "NEW RECIPE")
        }


    val itemPadding = 5.dp





    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(itemPadding),
        contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp)
    ) {

        item { EditTitleRow(recipeBookViewModel ,itemPadding, recipeDraft) }

        item { ImageSelector(recipeDraft) }

        item { EditIngredientsSection(recipeDraft, itemPadding) }

        item { EditMethodsSection(recipeDraft, itemPadding) }

    }
}




/**
 *
 *   LazyColumn Items
 *
 * */
@Composable
private fun EditTitleRow(
    recipeBookViewModel: RecipeBookViewModel,
    itemPadding: Dp,
    recipeDraft: Recipe
) {

    val recipeDraftTitle = remember { mutableStateOf(recipeDraft.name) }
    val coroutineScope = rememberCoroutineScope()


    Row(
        modifier = Modifier
            .padding(start = 15.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(0.25f)
                    .clickable {
                        recipeBookViewModel.navHostController.popBackStack()
                    },
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                border =
                BorderStroke(
                    width = 2.dp,
                    color = Color(0xFFFF0000)

                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 0.dp)
                                .size(30.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color(0xFF000000)
                        )
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 0.dp)
                                    .size(25.dp),
                                backgroundColor = Color(0x00000000),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(
                                    width = 2.dp,
                                    color = Color(0xFFFF0000)
                                ),

                                ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(end = 0.dp)
                                            .size(25.dp),
                                        painter = painterResource(id = R.drawable.ic_cancel),
                                        contentDescription = "Cancel Icon",
                                        tint = Color(0xFFFF0000)
                                    )
                                }

                            }

                            Row() {
                                Text(
                                    text = "CANCEL",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF000000)
                                )
                            }
                        }


                        
                    }


                }
            }
            
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            val isDialogOpen = remember { mutableStateOf(false)}
            val recipeDraftTime = remember { mutableStateOf(recipeDraft.recipeTime) }

            val sizeScale = 40.dp

            val recipeMinutes = remember { mutableStateOf( recipeDraftTime.value ) }
            val recipeHours = remember { mutableStateOf(0) }

            LaunchedEffect(key1 = recipeMinutes ) {
                if (recipeMinutes.value >= 60) {
                    recipeHours.value = recipeMinutes.value / 60
                    recipeMinutes.value = recipeMinutes.value%60
                }
            }

            /**
             *
             *    TODO
             *
             *  Adjust hours and minutes incrementation in separated variables,
             *  then increment recipeDraftTime
             *
             *  */

            ShowAlertDialog(
                isDialogOpen = isDialogOpen,
                title = "Recipe Preparation Time",
                dialogContent = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(sizeScale),
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "Tempo de Preparo ",
                            tint = Color(0xFF7A7A7A)
                        )
                    }

                    //Hours
                    VerticalNumberPicker(
                        width = sizeScale,
                        max = 99,
                        default = recipeDraftTime.value.div(60),
                        onValueChange = {
                            coroutineScope.launch(Dispatchers.Default) {
//                                recipeHours.value = recipeHours.value.plus(it)
                                recipeHours.value = it
                                recipeDraftTime.value = (recipeHours.value*60)+(recipeMinutes.value)
                            }
                        }
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Hour", fontSize = (sizeScale.value / 2).sp)
                    }

                    //Minutes
                    VerticalNumberPicker(
                        width = sizeScale,
                        max = 60,
                        default = recipeDraftTime.value.rem(60),
                        onValueChange = {
                            coroutineScope.launch(Dispatchers.Default) {
//                                recipeMinutes.value = recipeMinutes.value.plus(it)
                                recipeMinutes.value = it
                                recipeDraftTime.value = (recipeHours.value*60)+(recipeMinutes.value)
                            }

                        }
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "min", fontSize = (sizeScale.value / 2).sp)
                    }
                },
//                bottomBar = {
//                    Column() {
//                        Row() {
//
//                        }
//                        Row() {
//                            Button(
//                                onClick = {
//                                    isDialogOpen.value = false
//                                },
//                                modifier = Modifier
//                                    .fillMaxWidth(0.5f)
//                                    .height(50.dp)
//                                    .padding(10.dp),
//                                shape = RoundedCornerShape(5.dp),
//                                colors = ButtonDefaults.buttonColors(Purple500)
//                            ) {
//                                Text(
//                                    text = "Close",
//                                    color = Color.White,
//                                    fontSize = 12.sp
//                                )
//                            }
//                        }
//                    }
//
//                },
                defaultBottomBar = true
            )


            Card(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(0.3f)
                    .clickable { isDialogOpen.value = true },
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                border =
                BorderStroke(
                    width = 1.dp,
                    color = Color(0xFF888888)

                )
            ) {




                Row(modifier = Modifier
                    .height(25.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .height(20.dp),
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = "Tempo de Preparo ",
                        tint = Color(0xFF7A7A7A)
                    )



                    val recipeTimeNullString = " add time"


                    AnimatedVisibility(
                        visible = recipeDraftTime.value == 0,
                        enter = EnterTransition.None,
                        exit = ExitTransition.None
                    ) {
                        Text(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF7A7A7A),
                            text = recipeTimeNullString
                        )
                    }

                    AnimatedVisibility(
                        visible = recipeDraftTime.value != 0,
                        enter = EnterTransition.None,
                        exit = ExitTransition.None
                    ) {
                        Text(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF7A7A7A),
                            text =
                            if (recipeDraftTime.value < 60) {
                                " ${recipeDraftTime.value}min"
                            } else {
                                if ((recipeDraftTime.value%60) == 0) {
                                    " ${recipeDraftTime.value/60}h"
                                } else {
                                    " ${recipeDraftTime.value/60}h${recipeDraftTime.value%60}min"
                                }

                            }

                        )
                    }


                }
            }
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val isDialogOpen = remember { mutableStateOf(false)}


            ShowAlertDialog(
                isDialogOpen = isDialogOpen,
                title = "Recipe Name",
                dialogContent = {
                    RecipeInputText(
                        modifier = Modifier
                            .padding(itemPadding),
                        text = recipeDraftTitle.value,
                        label = "Recipe Title",
                        textAlignment = TextAlign.End,
                        textFontSize = 22.sp,
                        labelFontSize = 16.sp,
                        maxLines = 4,
                        onTextChange = {
                            coroutineScope.launch(Dispatchers.Default) {
                                recipeDraftTitle.value = it
                            }
                        }
                    )
                },
                defaultBottomBar = true
            )

            Card(
                modifier = Modifier
                    .height(40.dp)
                    .wrapContentWidth()
                    .clickable { isDialogOpen.value = true },
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                border =
                BorderStroke(
                    width = 1.dp,
                    color = Color(0xFF888888)

                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = recipeDraftTitle.value,
                        color = Color(0xFF000000),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.End
                    )

                }

            }
        }

    }


}

@Composable
private fun EditTitleDialogContent(
    itemPadding: Dp,
    recipeTitle: String
) {
    val recipeDraftTitle = remember { mutableStateOf(recipeTitle) }

    RecipeInputText(
        modifier = Modifier
            .padding(itemPadding),
        text = recipeDraftTitle.value,
        label = "Recipe Title",
        textAlignment = TextAlign.End,
        textFontSize = 22.sp,
        labelFontSize = 16.sp,
        maxLines = 4,
        onTextChange = { recipeDraftTitle.value = it }
    )
}




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageSelector(recipeDraft: Recipe) {



    val recipeDraftImage = remember { mutableStateOf(recipeDraft.image) }

    val noImage = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.no_image)




    val context = LocalContext.current

    var isCameraSelected = false
    var imageUri: Uri?
    var bitmap: Bitmap?


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        bitmap = null
        recipeDraftImage.value =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, imageUri!!))
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        }
        recipeDraft.image = recipeDraftImage.value
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { btm: Bitmap? ->
        bitmap = btm
        imageUri = null
        recipeDraftImage.value = bitmap
        recipeDraft.image = recipeDraftImage.value
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

                Row() {
                    Image(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit,
                        bitmap = noImage.asImageBitmap(),
                        contentDescription = "Recipe Image"
                    )
                }

                Column() {

                    AnimatedVisibility(
                        visible = (recipeDraftImage.value != null),
                        enter = EnterTransition.None,
                        exit = ExitTransition.None
                    ) {
                        if (recipeDraftImage.value != null) {
                            Image(
                                modifier = Modifier
                                    .padding(0.dp)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.FillWidth,
                                bitmap = recipeDraftImage.value!!.asImageBitmap(),
                                contentDescription = "Recipe Image"
                            )
                        }

                    }
                }



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



                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly

                        ) {
                            SelectMediaSourceCardButton(
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
                                            isCameraSelected = false
                                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                        }
                                    }
                                    isDialogOpen.value = false
                                }
                            )

                            Spacer(modifier = Modifier.padding(5.dp))

                            SelectMediaSourceCardButton(
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
                                            isCameraSelected = true
                                            permissionLauncher.launch(Manifest.permission.CAMERA)
                                        }
                                    }
                                    isDialogOpen.value = false
                                }
                            )


                        }
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
                                    recipeDraftImage.value = null
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
                        }/** DELETE Image Button*/

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
                    }/** CHANGE Image Button*/


                }








            }

        }




    }


}



@Composable
private fun SelectMediaSourceCardButton(
    cardHeight: Dp,
    cardWidth: Dp,
    iconID: Int,
    iconSize: Dp,
    contentDesc: String,
    buttonText: String,
    onClick: () -> Unit

) {
    Card(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        border = BorderStroke(1.dp, Color(0xFF888888))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(iconSize),
                painter = painterResource(iconID),
                contentDescription = contentDesc
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(text = buttonText)

        }

    }
}


@Composable
private fun EditIngredientsSection(
    recipeDraft: Recipe,
    itemPadding: Dp
) {

    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(itemPadding))



        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(40.dp)
                .fillMaxSize()
                .clickable { isVisible = !isVisible },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = 14.dp),
                text = "Ingredients",
                color = Color(0xFF000000),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .rotate(
                        if (isVisible) {
                            0f
                        } else {
                            -90f
                        }
                    ),
                imageVector =
                if (isVisible) {
                    Icons.Default.KeyboardArrowDown
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                contentDescription = "Arrow Icon"
            )



        }

        recipeDraft.ingredients.forEach { ingredient ->
            ingredient.adjustPortion()

            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                AnimatedCardView(
                    isNotSelectedContent = { IngredientIsNotSelectedContent(ingredient) },
                    isSelectedContent = { IngredientIsSelectedContent(ingredient, onTextQuantityChange = {}) },
                    isSelectedBorderColor = Color(0xFF0022A3),
                    fillMaxWidthFloat = 0.9f
                )
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                Spacer(modifier = Modifier.padding(itemPadding))
            }
        }


        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            

            
            AddNewItemButtonCard(
                buttonText = "Ingredient",
                minHeight = 60.dp,
                onClick = {
                          
                },
                fillMaxWidthFloat = 0.9f
            )
            
            
            
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }







    }




}

@Composable
private fun EditMethodsSection(
    recipeDraft: Recipe,
    itemPadding: Dp
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(itemPadding))

        var isVisible by remember { mutableStateOf(true) }

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(40.dp)
                .fillMaxSize()
                .clickable { isVisible = !isVisible },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = 14.dp),
                text = "Preparation Methods",
                color = Color(0xFF000000),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .rotate(
                        if (isVisible) {
                            0f
                        } else {
                            -90f
                        }
                    ),
                imageVector =
                if (isVisible) {
                    Icons.Default.KeyboardArrowDown
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                contentDescription = "Arrow Icon"
            )
        }

//        val recipeDraftMethods = emptyList<String>().toMutableList()
        val recipeDraftMethods = remember { mutableListOf<String>() }

        recipeDraft.recipeMethods.forEach { method ->
            recipeDraftMethods.add(method)
        }

        recipeDraftMethods.forEachIndexed { index, method ->

            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                AnimatedCardView(
                    isNotSelectedContent = { MethodIsNotSelectedContent(itemPadding, index, method) },
                    isSelectedContent = { MethodIsSelectedContent(itemPadding, method) },
                    isSelectedBorderColor = Color(0xFF0022A3),
                    fillMaxWidthFloat = 0.9f
                )
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                Spacer(modifier = Modifier.padding(itemPadding))
            }




        }

        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {

            val isDialogOpen = remember { mutableStateOf(false) }
            val newMethod = remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()

            ShowAlertDialog(
                title = "Add method",
                isDialogOpen = isDialogOpen,
                dialogContent = {
                    Surface() {
                        Row() {
                            RecipeInputText(
                                text = newMethod.value,
                                label = "new method",
                                onTextChange = { newMethod.value = it })
                        }
                    }
                },
                defaultBottomBar = true,
                bottomBar = {
                    Button(
                        onClick = {
                            coroutineScope.launch(Dispatchers.Default) {
                                recipeDraftMethods.add(newMethod.value)
                            }
                            isDialogOpen.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF0022A3))
                    ) {
                        Text(
                            text = "ADD Method",
                            color = Color(0xFFFFFFFF),
                            fontSize = 12.sp
                        )
                    }
                }
            )

            AddNewItemButtonCard(
                buttonText = "Method",
                minHeight = 70.dp,
                onClick = { isDialogOpen.value = true },
                fillMaxWidthFloat = 0.9f
            )
        }

        Spacer(modifier = Modifier.padding(itemPadding))
        Spacer(modifier = Modifier.padding(itemPadding))

    }
}
/** LazyColum Items */






/**
 *
 *
 *   Input and Buttons
 *
 * */
@Composable
private fun AddNewItemButtonCard(
    buttonText: String,
    fillMaxWidthFloat: Float = 1f,
    minHeight: Dp = 50.dp,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .defaultMinSize(minHeight = minHeight)
            .fillMaxWidth(fillMaxWidthFloat)
            .clickable {
                onClick.invoke()
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color(0xFF0022A3))
    ) {
        Row(
            modifier = Modifier
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 35.dp, end = 20.dp)
                    .size(35.dp),
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = "Add Button Icon",
                tint = Color(0xFF0022A3)
            )
            Text(
                text = buttonText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF000000)
            )
        }
    }
}


@Composable
private fun AddNewMethodTextFieldRow(context: Context) {
    var newMethod by remember { mutableStateOf("") }



    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RecipeInputText(
            text = newMethod,
            labelFontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 10.dp, bottom = 5.dp),
            label = "add new method",
            onTextChange = {
                if (it.all { char ->
                        char.isLetterOrDigit() || char.isWhitespace()
                    }) newMethod = it
            }
        )
        Icon(
            modifier = Modifier
                .size(30.dp)
                .fillMaxWidth(0.3f)
                .clickable {
                    if (newMethod.isNotEmpty()) {
                        //                          onAddMethod(newMethod)
                        newMethod = ""
                        Toast
                            .makeText(context, "NEW METHOD Added", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
            imageVector = Icons.Rounded.AddCircle,
            contentDescription = "Add Button Icon",
            tint = Color(0xFF0022A3)
        )
    }
}

/**   Input and Buttons  */






/**
 *
 *
 *   AnimatedCardView Contents
 *
 * */
@Composable
private fun IngredientIsNotSelectedContent(ingredient: Ingredient) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IngredientItem(
                ingredient = ingredient,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun IngredientIsSelectedContent(
    ingredient: Ingredient,
    textSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    onTextQuantityChange: (String) -> Unit,

) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
            modifier = Modifier
                .wrapContentHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            //Ingredient Line
            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {


                //Ingredient Info
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Row {

                        //Check if Ingredient have its own measure or how to use text
                        if (ingredient.textHowToMeasure != null) {






                            //Ingredient text How To Use/Measure
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(modifier = Modifier
                                    .width(80.dp)
                                    .height(50.dp)
                                ) {

                                    TextField(
                                        modifier = Modifier
                                            .height(50.dp),
                                        value = ingredient.textHowToMeasure!!,
                                        onValueChange = {},
                                        singleLine = true,
                                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x00FFFFFF)),
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            imeAction = ImeAction.Done
                                        ),
                                        keyboardActions = KeyboardActions(onDone = {
                                            keyboardController?.hide()
                                        }),
                                        textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                                    )
                                }




                            }


                            //Show ingredient quantity
                        } else {

                            Row() {
                                //Quantity
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.End
                                ) {

                                    val df = DecimalFormat("##")


                                    Text(
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Italic,
                                        color = Color(0xFF2C2C2C),
                                        fontSize = textSize,
                                        fontWeight = fontWeight,
                                        text =
                                        when {
                                            ingredient.isUnit -> {
                                                if (ingredient.quantity == floor(ingredient.quantity!!)) {
                                                    df.format(ingredient.quantity)
                                                } else {
                                                    "${ingredient.quantity}"
                                                }
                                            }
                                            ingredient.isLiquid -> {
                                                if (ingredient.volumeInMilliliters!! >= 1000) {
                                                    if (ingredient.volumeInLiters == floor(
                                                            ingredient.volumeInLiters!!
                                                        )
                                                    ) {
                                                        df.format(ingredient.volumeInLiters)
                                                    } else {
                                                        "${ingredient.volumeInLiters}"
                                                    }
                                                } else {
                                                    "${ingredient.volumeInMilliliters}"
                                                }
                                            }
                                            ingredient.isWeight -> {
                                                if (ingredient.weightInGrams!! >= 1000) {
                                                    if (ingredient.weightInKg == floor(
                                                            ingredient.weightInKg!!
                                                        )
                                                    ) {
                                                        df.format(ingredient.weightInKg)
                                                    } else {
                                                        "${ingredient.weightInKg}"
                                                    }
                                                } else {
                                                    "${ingredient.weightInGrams}"
                                                }

                                            }
                                            else -> {
                                                ""
                                            }
                                        }
                                    )
                                }

                                Divider(
                                    modifier = Modifier
                                        .width(5.dp),
                                    color = Color(0x00FFFFFF)
                                )

                                //Unit
                                Column(
                                    modifier = Modifier
                                        .width(60.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Italic,
                                        color = Color(0xFF2C2C2C),
                                        fontSize = textSize,
                                        fontWeight = fontWeight,
                                        text =
                                        when {
                                            ingredient.isUnit -> {
                                                " Un."
                                            }
                                            ingredient.isLiquid -> {
                                                if (ingredient.volumeInMilliliters!! >= 1000) {
                                                    " L"
                                                } else {
                                                    " ml"
                                                }

                                            }
                                            ingredient.isWeight -> {
                                                if (ingredient.weightInGrams!! >= 1000) {
                                                    " Kg"
                                                } else {
                                                    " g"
                                                }
                                            }
                                            else -> {
                                                ""
                                            }
                                        }
                                    )

                                }//Column

                            }//row
                        }//else


                    }//row

                }//Column - Ingredient Info

                //Ingredient Name
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = 6.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    TextField(
                        modifier = Modifier
                            .height(50.dp),
                        value = ingredient.name,
                        onValueChange = {},
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x00FFFFFF)),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                        }),
                        textStyle = TextStyle(fontSize = 12.sp),
                    )


                }//Ingredient Name


            }//Line - texts


        }//Ingredient Column

        Icon(
            modifier = Modifier
                .padding(end = 25.dp)
                .size(25.dp)
                .clickable {
                    /* TODO */
                },
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Icon",
            tint = Color(0xFFC00000)

        )
    }

}

@Composable
private fun MethodIsNotSelectedContent(
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

@Composable
private fun MethodIsSelectedContent(
    itemPadding: Dp,
    method: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            RecipeInputText(
                modifier = Modifier
                    .padding(itemPadding),
                text = method,
                textAlignment = TextAlign.Center,
                textFontSize = 22.sp,
                labelFontSize = 16.sp,
                maxLines = 15,
                onTextChange = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(45.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        /* TODO */
                    },
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color(0xFFC00000)

            )
        }
    }
}
/**   AnimatedCardView Contents  */









@Preview(showBackground = true)
@Composable
fun ScreenAddEditRecipePreview() {

    val recipeList = viewModel<RecipeBookViewModel>().recipeList.collectAsState().value

   // ScreenAddEditRecipe( recipe = recipeList[0] )





}
