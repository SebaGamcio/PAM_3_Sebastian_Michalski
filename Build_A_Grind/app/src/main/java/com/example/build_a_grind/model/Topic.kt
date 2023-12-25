package com.example.build_a_grind.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val courseName: Int,
    val number: Int,
    @DrawableRes val courseImage: Int
)
