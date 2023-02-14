package com.jetpack.uijetpackcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction

/**
 * Created by Aashis on 14,February,2023
 */

@Composable
fun HomeScreen() {
    Box() {
        SearchSection()
    }
}

@Composable
fun SearchSection() {
    TextField(
        value = "",
        onValueChange = { },
        label = Text(text = "Search"),
        leadingIcon = Icon(Icons.Default.Search, "Search Icon"),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            // Perform search with searchText
        })
    )
}
