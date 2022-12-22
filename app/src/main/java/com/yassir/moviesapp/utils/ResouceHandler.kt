package com.yassir.moviesapp.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes

interface ResourceHandler {

    fun getString(@StringRes textID: Int, vararg formatArgs: Any): String

    fun getString(@StringRes textID: Int): String

    fun getInteger(@IntegerRes id: Int): Int

    fun getColor(@ColorRes id: Int): Int

    fun getDrawable(@DrawableRes id: Int): Drawable?
}