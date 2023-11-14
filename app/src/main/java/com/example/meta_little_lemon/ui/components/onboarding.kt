package com.example.meta_little_lemon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meta_little_lemon.R

@Composable
fun Header() {

    //-header
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(30.dp)
                .padding(start = 5.dp, end = 5.dp)
        )
        Text(text = "Little Lemon")
    }


}

@Composable
fun HeaderWithBg() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) { Text(text = "Lets get to know you", modifier = Modifier.padding(20.dp)) }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfo() {
    Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
        Row { Text(text = "Personal Info", Modifier.padding(
            top = 20.dp, bottom = 20.dp)) }
        OutlinedTextField(
            value = "ala",
            onValueChange = {},
            label = { Text(text = "Label") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        )
        OutlinedTextField(
            value = "ala",
            onValueChange = {},
            label = { Text(text = "Label") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        )
        OutlinedTextField(
            value = "ala",
            onValueChange = {},
            label = { Text(text = "Label") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    Column {
        Header()
        HeaderWithBg()
        PersonalInfo()
        Box(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, bottom = 20.dp).align(Alignment.BottomCenter)){
                Text(text ="Label" )
            }
        }

    }

}