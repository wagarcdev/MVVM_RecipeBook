package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ImageSelectorAlertDialogContent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import kotlinx.coroutines.launch

@Composable
fun DrawerMainMenuContent(
    signInGoogleViewModel: SignInViewModel
) {
    val shape = RoundedCornerShape(topEnd = 100.dp)

    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxWidth(1f)
            .fillMaxHeight()
            .clip(shape)
            .offset(x = (-11).dp, y = 11.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .border(6.dp, RB_Black_25, shape)
        ) {
            Row() {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.wood_texture),
                    contentDescription = "background",
                    contentScale = ContentScale.FillBounds
                )
            }

            val blackLineBorder = 5.dp

            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(top = 32.dp, end = 32.dp)
                    .border(blackLineBorder, RB_Black_80, RoundedCornerShape(topEnd = 80.dp))
                ,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DrawerMMCHeader(signInGoogleViewModel)

                Spacer(modifier = Modifier.height(12.dp))
                Divider(
                    modifier = Modifier
                        .offset(x = -(5).dp)
                        .fillMaxWidth(1f),
                    color = RB_Black_82,
                    thickness = 5.dp
                )
                Spacer(modifier = Modifier.height(4.dp))

                DrawerMMCBody(signInGoogleViewModel)
            }

        }
    }





}


@Composable
private fun DrawerMMCHeader(signInGoogleViewModel: SignInViewModel) {

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val isDialogOpen = remember { mutableStateOf(false) }


    var isCameraSelected = false
//    var imageUri: Uri?
    var bitmap: Bitmap?
//    var displayBitmap = remember { mutableStateOf<Bitmap?>(null)}
    var photoFilepath: String? = null

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

//        imageUri = uri
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
            photoFilepath?.let {
                signInGoogleViewModel.userProfileImage.value = it
            }
        }

    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { btm: Bitmap? ->

//        bitmap = btm
//        displayBitmap.value = bitmap

        // CALL THIS METHOD TO GET THE ACTUAL PATH
        photoFilepath = MediaStore.Images.Media.insertImage(context.getContentResolver(), btm, "Title", null)

        coroutineScope.launch {
            photoFilepath?.let {
                signInGoogleViewModel.userProfileImage.value = it
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


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 4.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                shape = CircleShape,
                border = BorderStroke(4.dp, RB_Black_82),
                contentColor = RB_Transparent,
                backgroundColor = RB_Transparent,
                modifier = Modifier
                    .size(120.dp)
                    .clickable { isDialogOpen.value = true }
            ) {
                Card(
                    shape = CircleShape,
                    contentColor = RB_Transparent,
                    backgroundColor = RB_Transparent,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(1f),
                        model =
                        if (signInGoogleViewModel.userProfileImage.value == "") {
                            R.drawable.blank_profile_picture
                        } else {
                            signInGoogleViewModel.userProfileImage.value
                        },
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        filterQuality = FilterQuality.Medium
                    )
                }

            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "TODO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = RB_Black_82
                )

                Text(
                    text = "get user name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RB_Black_82
                )

            }


        }
    }



}


@Composable
fun DrawerMMCBody(
    signInGoogleViewModel: SignInViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        /** TODO onCLick */
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            repeat(4) { count ->
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .defaultMinSize(minHeight = 50.dp)
                            .padding(vertical = 4.dp)
                            .border(2.dp, RB_Black_82, RoundedCornerShape(50.dp))
                            .clickable { /** TODO onCLick */ },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Menu Item ${count+1}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = RB_Black_82
                        )
                    }

                }
            }


        }
    }

    Spacer(modifier = Modifier.height(18.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .height(150.dp)
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_techcheftools_logo_simple),
            contentDescription = "logo",
            tint = RB_Black_82
        )
    }

    Spacer(modifier = Modifier.height(18.dp))


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .defaultMinSize(minHeight = 40.dp)
                .border(2.dp, RB_Black_82, RoundedCornerShape(50.dp))
                .clickable {
                    coroutineScope.launch {
                        signInGoogleViewModel.signOut(context)
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "   <-   Sign Out",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = RB_Black_82
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Version 1.0.0",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RB_Black_70
        )
    }
}

