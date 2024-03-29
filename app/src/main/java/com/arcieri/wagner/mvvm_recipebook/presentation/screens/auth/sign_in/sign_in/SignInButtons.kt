package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.sign_in

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.google_one_tap_sign_in.OneTapSignInState
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.MainMenuButton
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.GoogleButton
import kotlinx.coroutines.launch

@Composable
fun SignInButtons(
    signInGoogleViewModel: SignInViewModel,
    wannaRegister: MutableState<Boolean>,
    forgotPassword: MutableState<Boolean>,
    oneTapSignInState: OneTapSignInState,
    isError: Boolean = false
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    val textFieldColors = RB_OrangeLightExtra

    val buttonFillWidthFloat = 0.7f

    Column(
        modifier = Modifier
            .imePadding()
            .navigationBarsPadding()
            .defaultMinSize(300.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly

        ) {
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(buttonFillWidthFloat),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Hello!",
                    color = RB_OrangeLightExtra,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 18.dp)
                    .fillMaxWidth(buttonFillWidthFloat),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Please, sign in to continue",
                    color = RB_OrangeLight,
                    fontSize = 10.sp,
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            item {


                /** Sign In Google Button*/

                GoogleButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .requiredWidthIn(max = 300.dp),
                    onClick = oneTapSignInState::open,
                    text = stringResource(id = R.string.continueWithGoogle),
                    enabled = !oneTapSignInState.opened,
                    isLoading = oneTapSignInState.opened
                )

                when {
                    isError -> {
                        isError.let {

                            Toast.makeText(
                                LocalContext.current,
                                stringResource(R.string.auth_error_msg),
                                Toast.LENGTH_LONG
                            ).show()

                            signInGoogleViewModel.hideLoading()
                        }
                    }
                }
            }


            item {
                Row(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "or", color = RB_White)
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = {
                            coroutineScope.launch {
                                email.value = it
                            }
                        },
                        label = { Text(text = "Email", color = textFieldColors) },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = textFieldColors,
                            cursorColor = textFieldColors,
                            focusedLabelColor = textFieldColors,
                            unfocusedLabelColor = textFieldColors,
                            backgroundColor = RB_Black
                        )
                    )

                    OutlinedTextField(
                        value = password.value,
                        onValueChange = {
                            coroutineScope.launch {
                                password.value = it
                            }
                        },
                        label = { Text(text = "Password", color = textFieldColors) },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = textFieldColors,
                            cursorColor = textFieldColors,
                            focusedLabelColor = textFieldColors,
                            unfocusedLabelColor = textFieldColors,
                            backgroundColor = RB_Black
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        horizontalArrangement = Arrangement.End
                    ) {

                        Text(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        forgotPassword.value = true
                                    }
                                },
                            text = "Forgot Password?",
                            color = RB_White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }

                    MainMenuButton(
                        buttonVerticalPaddingDp = 20.dp,
                        buttonFillMaxWidthFloat = buttonFillWidthFloat,
                        iconID = R.drawable.ic_log_in,
                        iconDescription = "Sign In Button",
                        text = "Sign In",
                        textAlign = TextAlign.Center,
                        textFillMaxWidthFloat = 0.85f,
                        borderStroke = BorderStroke(0.dp, Color.Transparent),
                        leftGradientColor = RB_OrangeDarker,
                        centerLeftGradientColor = RB_OrangeDark,
                        centerGradientColor = RB_OrangeDark,
                        centerRightGradientColor = RB_Orange,
                        rightGradientColor = RB_OrangeLight,
                        textFontColor = RB_White
                    ) { /*TODO*/ }

                    Row(
                        modifier = Modifier
                            .wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End

                    ) {
                        Text(
                            text = "Don't have an account?  ",
                            color = RB_OrangeLight,
                            fontSize = 10.sp
                        )

                        Text(
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch {
                                        wannaRegister.value = true
                                    }
                                },
                            text = "Register now! It's free!",
                            color = RB_OrangeLightExtra,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                }
            }

            item {
                Spacer(Modifier.height(20.dp))
            }
        }
    }


}