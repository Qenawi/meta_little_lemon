package com.example.meta_little_lemon.ui.components

import android.app.Application
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.meta_little_lemon.R
import com.example.meta_little_lemon.ui.theme.PrimaryGreen
import com.example.meta_little_lemon.ui.theme.PrimaryYellow
import com.example.meta_little_lemon.ui.theme.PurpleGrey40
import com.example.meta_little_lemon.ui.theme.Secondary1


@Composable
fun Home(navController: NavHostController) {


   // val viewModel: MyViewModel = viewModel()
   // val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    val searchPhrase = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {}
    Column {
        Header(navController)
        UpperPanel{ searchPhrase.value = it }
        LowerPanel(arrayListOf("Startes","Main","Kyc"), searchPhrase)
    }


}

@Composable
fun Header(navController: NavHostController){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center)
    {
        Image(painter = painterResource(id = R.drawable.lemon),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .size(30.dp)
        )
        Text(text = stringResource(id = R.string.app_name) , style = MaterialTheme.typography.headlineMedium)

    }//Row
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpperPanel(search : (parameter: String)-> Unit) {
    val searchPhrase = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .background(PrimaryGreen)
        .padding(horizontal = 20.dp, vertical = 10.dp)) {
        Text(text = "Little Lemon", style = MaterialTheme.typography.headlineSmall, color = PrimaryYellow)
        Text(text = "New York", style = MaterialTheme.typography.headlineLarge, color = Color.White)
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with  a modern twist. Turkish, Italian, Indian and chinese recipes are our speciality.",
                modifier = Modifier.fillMaxWidth(0.7f),
                color = Color.White,
                style = MaterialTheme.typography.bodySmall)
            Image(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(value = searchPhrase.value,
            onValueChange = {
                searchPhrase.value = it
                search(searchPhrase.value)
            },
            placeholder = {
                Text(text = "Enter Search Phrase")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(

                containerColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth())

    }

}

@Composable
fun LowerPanel(databaseMenuItems: List<String>, search: MutableState<String>) {
    val categories = databaseMenuItems.map {
        it.uppercase()
    }.toSet()


    val selectedCategory = remember {
        mutableStateOf("")
    }


    val items = if(search.value == ""){
        databaseMenuItems

    }
    else{
        databaseMenuItems.filter {
            it.contains(search.value, ignoreCase = true)

        }


    }



    val filteredItems = if(selectedCategory.value == "" || selectedCategory.value == "all"){
        items
    }
    else{
        items.filter {
            it.contains(selectedCategory.value, ignoreCase = true)
        }
    }


    Column {
        MenuCategories(categories) {selectedCategory.value = it }
        Spacer(modifier = Modifier.size(2.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for (item in filteredItems){
                MenuItem(item = item)
            }
        }

    }
}


@Composable
fun MenuCategories(categories: Set<String>, categoryLambda: (sel: String) -> Unit) {
    val cat = remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(text = "ORDER FOR DELIVERY", style =  MaterialTheme.typography.headlineMedium)

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            CategoryButton(category = "All") {
                cat.value = it.lowercase()
                categoryLambda(it.lowercase())
            }

            for (category in categories) {
                CategoryButton(category = category) {
                    cat.value = it
                    categoryLambda(it)
                }

            }

        }
    }
}

@Composable
fun CategoryButton(category:String, selectedCategory: (sel: String) -> Unit) {
    val isClicked = remember{
        mutableStateOf(false)
    }
    Button(onClick = {
        isClicked.value = !isClicked.value
        selectedCategory(category)

    },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = PrimaryGreen
        )) {
        Text(text = category)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: String) {

    val itemDescription = item

    Card(
        colors = CardDefaults.cardColors(containerColor = PrimaryGreen , contentColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .clickable {}
            .padding(4.dp),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = "title", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "description", modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "$ ${item}", fontWeight = FontWeight.Bold)

            }

            Image(
                painter = painterResource(id = R.drawable.lemon),
                contentDescription = "",
                contentScale = ContentScale.Fit, modifier = Modifier.size(20.dp)
            )

        }
    }

}
