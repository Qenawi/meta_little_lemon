package com.example.meta_little_lemon.ui.components

import android.content.Context
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavHostController
import com.example.meta_little_lemon.R
import com.example.meta_little_lemon.ui.theme.PrimaryGreen


@Composable
fun rememberImeState(): State<Boolean> {
    val imeState = remember {
        mutableStateOf(false)
    }

    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            imeState.value = isKeyboardOpen
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return imeState
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(context: Context, navHostController: NavHostController) {
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val firstName = remember {
        mutableStateOf("")
    }

    val lastName = remember {
        mutableStateOf("")
    }

    val email = remember {
        mutableStateOf("")
    }

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()


    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }


    Column(
        Modifier
            .fillMaxSize().background(Color.White)
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.logoa),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(150.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }
        Row(
            modifier = Modifier.height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Let's get to know you",
                style = MaterialTheme.typography.headlineMedium,
                color = PrimaryGreen
            )
        }

        Text(
            text = "Personal Information",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge
        )
        OutlinedTextField(
            value = firstName.value,
            onValueChange = {
                firstName.value = it
            },
            label = { Text(text = "First Name") },
            singleLine = true,
            placeholder = { Text(text = "John") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = PrimaryGreen,
                focusedBorderColor = PrimaryGreen
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName.value,
            onValueChange = {
                lastName.value = it
            },
            label = { Text(text = "Last Name") },
            singleLine = true,
            placeholder = { Text(text = "Doe") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = PrimaryGreen,
                focusedBorderColor = PrimaryGreen
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = { Text(text = "Email") },
            singleLine = true,
            placeholder = { Text(text = "johndoe@gmail.com") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = PrimaryGreen,
                focusedBorderColor = PrimaryGreen
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(40.dp))

        Button(
            onClick = {
                if (
                    firstName.value.isNotEmpty() &&
                    lastName.value.isNotEmpty() &&
                    email.value.isNotEmpty()

                ) {
                    sharedPreferences.edit()
                        .putString("firstName", firstName.value)
                        .putString("lastName", lastName.value)
                        .putString("email", email.value)
                        .putBoolean("userRegistered", true)
                        .apply()

                    Toast.makeText(
                        context,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()


                     navHostController.navigate("Home"){
                         popUpTo("onBoarding"){inclusive = true}
                         launchSingleTop = true
                     }

                } else {
                    Toast.makeText(
                        context,
                        "Invalid Details, Please try again",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Register")
        }
    }
}
