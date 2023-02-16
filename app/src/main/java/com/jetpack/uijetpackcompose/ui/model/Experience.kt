package com.jetpack.uijetpackcompose.ui.model

import androidx.annotation.DrawableRes

/**
 * Created by Aashis on 16,February,2023
 */
data class Experience(
    val title: String,
    val subTitle: String,
    @DrawableRes val iconId: Int,
    val fav: Boolean,
    var sizes: String,
    var rating: Int
)
