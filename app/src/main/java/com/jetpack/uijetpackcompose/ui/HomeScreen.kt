package com.jetpack.uijetpackcompose.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.uijetpackcompose.R
import com.jetpack.uijetpackcompose.ui.model.Experience
import com.jetpack.uijetpackcompose.ui.model.Features
import com.jetpack.uijetpackcompose.ui.theme.*

/**
 * Created by Aashis on 14,February,2023
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    Box( // or whatever your parent composable is
        modifier = Modifier
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
//                .verticalScroll(rememberScrollState())
        ) {
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
                    Experience("This is title", "", R.drawable.houses, false, "", ""),
                    Experience("This is title", "", R.drawable.houses, false, "", ""),
                    Experience("This is title", "", R.drawable.houses, false, "", "")
                )
            )
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            MyBottomNavigation()
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
        contentPadding = PaddingValues(start = 20.dp, end = 7.5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(listOfExperience.size) {
            ExperienceSection(listOfExperience[it])
        }
    }
}

@Composable
fun ExperienceSection(experience: Experience) {
    Column(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .padding(end = 9.dp)
                .background(
                    Color(R.color.black)
                )
        ) {
            Image(
                painter = painterResource(id = experience.iconId),
                contentDescription = "My Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), contentScale = ContentScale.Crop
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_save),
                contentDescription = experience.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Text(
            text = "What can we help you find",
            color = TextGrey,
            modifier = Modifier.padding(top = 5.dp),
            fontSize = 15.sp
        )
        Text(
            text = "What can we help you find, Sandor?",
            color = Color.Black,
            modifier = Modifier.padding(top = 2.dp),
            fontSize = 15.sp, fontWeight = FontWeight.Bold
        )
        Text(
            text = "What can we help you find, Sandor?",
            color = TextColor,
            modifier = Modifier.padding(top = 2.dp),
            fontSize = 13.sp, fontWeight = FontWeight.Thin
        )
        RatingSystem(5, {})
    }
}


@Composable
fun RatingSystem(
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit
) {
    var currentRating by remember { mutableStateOf(0) }

    Row {
        for (i in 1..maxRating) {
            if (i <= currentRating) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Filled Star",
                    tint = StarColor,
                    modifier = Modifier
                        .clickable {
                            currentRating = i
                            onRatingChanged(i)
                        }
                        .height(13.dp)
                        .width(13.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Outlined Star",
                    tint = StarColor,
                    modifier = Modifier
                        .clickable {
                            currentRating = i
                            onRatingChanged(i)
                        }
                        .height(13.dp)
                        .width(13.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
        }
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

@Composable
fun MyBottomNavigation() {
    BottomNavigation(backgroundColor = ColorWhite) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = "Home",
                    Modifier.size(24.dp)
                )
            },
            label = { Text("SEARCH") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_fav),
                    contentDescription = "Search",
                    Modifier.size(24.dp)
                )
            },
            label = { Text("SAVE") },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_trips),
                    contentDescription = "TRIPS", Modifier.size(24.dp)
                )
            },
            label = { Text("TRIPS") },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_inbox),
                    contentDescription = "Profile",
                    Modifier.size(24.dp)
                )
            },
            label = { Text("INBOX") },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Profile"

                )
            },
            label = { Text("PROFILE") },
            selected = false,
            onClick = { /*TODO*/ }
        )
    }
}

