package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.forgot

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.MainMenuButton
import com.arcieri.wagner.mvvm_recipebook.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordButtons(forgotPassword: MutableState<Boolean>) {

    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmation = remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    val buttonFillWidthFloat = 0.7f

    val textFieldColors = RB_OrangeLightExtra

    Column(
        modifier = Modifier
            .imePadding()
            .defaultMinSize(300.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
            ,
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
                    text = "Forgot your password?",
                    color = RB_White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 18.dp)
                    .fillMaxWidth(buttonFillWidthFloat),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Please, enter you mail to generate a link to reset your password",
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f),
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



                }
            }



            item {
                MainMenuButton(
                    buttonVerticalPaddingDp = 20.dp,
                    buttonFillMaxWidthFloat = buttonFillWidthFloat,
                    iconID = R.drawable.ic_log_in,
                    iconDescription = "Send reset link button",
                    text = "Get Reset Link",
                    textAlign = TextAlign.Center,
                    textFillMaxWidthFloat = 0.85f,
                    borderStroke = BorderStroke(0.dp, Color.Transparent),
                    leftGradientColor = RB_OrangeDarker,
                    centerLeftGradientColor = RB_OrangeDark,
                    centerGradientColor = RB_OrangeDark,
                    centerRightGradientColor = RB_Orange,
                    rightGradientColor = RB_OrangeLight,
                    fontColor = RB_White,
                    onClick = { /*TODO*/ }
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 4.dp, bottom = 18.dp)
                        .fillMaxWidth(buttonFillWidthFloat),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                            .clickable { coroutineScope.launch { forgotPassword.value = false } },
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back to Log In",
                        tint = RB_OrangeLight

                    )

                    Text(
                        modifier = Modifier
                            .clickable { coroutineScope.launch { forgotPassword.value = false } },
                        text = "Go back to  ",
                        color = RB_OrangeLight,
                        fontSize = 10.sp
                    )


                    Text(
                        modifier = Modifier
                            .clickable { coroutineScope.launch { forgotPassword.value = false } },
                        text = "Log In",
                        color = RB_OrangeLightExtra,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }

            item {
                Spacer(Modifier.height(20.dp))
            }
        }
    }

}