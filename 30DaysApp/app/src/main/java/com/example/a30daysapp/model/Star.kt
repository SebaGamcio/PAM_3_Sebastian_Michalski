package com.example.a30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.painter.Painter

data class Star(
    @StringRes val day: Int,
    @StringRes val starName: Int,
    @StringRes val starDes: Int,
    @DrawableRes val starImage: Int,
)
