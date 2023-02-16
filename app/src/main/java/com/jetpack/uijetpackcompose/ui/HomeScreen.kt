package com.jetpack.uijetpackcompose.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.uijetpackcompose.R
import com.jetpack.uijetpackcompose.ui.model.Experience
import com.jetpack.uijetpackcompose.ui.model.Features
import com.jetpack.uijetpackcompose.ui.theme.ColorBoxBorder
import com.jetpack.uijetpackcompose.ui.theme.ColorWhite

/**
 * Created by Aashis on 14,February,2023
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Box(modifier = Modifier.padding(10.dp)) {
        Column {
            SearchSection()
            ChipSection(types = mutableListOf("Dates", "Guests"))
            FeatureSection(
                listOf(
                    Features("Houses", R.drawable.houses),
                    Features("Experiences", R.drawable.experience),
                    Features("Restaurant", R.drawable.restaurant)
                )
            )
            TopRatedExperience(
                listOf(
                    Experience("This is title", "", R.drawable.houses, false, "", "")
                )
            )
        }
    }
}

@Composable
fun TopRatedExperience(listOfExperience: List<Experience>) {
    Text(
        text = "Top-rated experiences",
        modifier = Modifier.padding(start = 20.dp),
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(listOfExperience.size) {
            ExperienceSection(listOfExperience[it])
        }
    }
}

@Composable
fun ExperienceSection(experience: Experience) {
    Column() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = experience.iconId), contentDescription = "My Image"
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = experience.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
        Text(
            text = "What can we help you find, Sandor?",
            color = Color.Gray,
            modifier = Modifier.padding(15.dp),
            fontSize = 15.sp
        )
        Text(
            text = "What can we help you find, Sandor?",
            color = Color.Black,
            modifier = Modifier.padding(15.dp),
            fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        Text(
            text = "What can we help you find, Sandor?",
            color = Color.Blue,
            modifier = Modifier.padding(15.dp),
            fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SearchSection() {

    val text = remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Back button"
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            // Perform search with searchText
        }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
    )
}

@Composable
fun ChipSection(types: List<String>) {
    LazyRow {
        items(types.size) {
            Box(modifier = Modifier
                .padding(start = 10.dp)
                .clickable {}
                .border(
                    BorderStroke(1.dp, ColorBoxBorder), shape = RoundedCornerShape(2.dp)
                )
                .background(ColorWhite)
                .padding(7.dp)) {
                Text(text = types[it], color = Color.Black)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Features>) {
    Column() {
        Text(
            text = "What can we help you find, Sandor?", modifier = Modifier.padding(15.dp)
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.height(150.dp)
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(feature: Features) {

    Box(
        modifier = Modifier.padding(end = 20.dp)
    ) {
        Column() {
            Image(
                painter = painterResource(feature.iconId),
                contentDescription = "My Image",
//                modifier = Modifier.fillMaxWidth()
            )
            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
            ) {
                Text(
                    text = feature.title, modifier = Modifier.fillMaxWidth(), color = Color.Black
                )
            }
        }
    }
}
