package com.yassir.moviesapp.wrappers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.yassir.moviesapp.utils.ResourceHandler

class AndroidResourceHandler(private val context: Context) : ResourceHandler {

    override fun getString(textId: Int, vararg formatArgs: Any): String =
        context.getString(textId, *formatArgs)

    override fun getString(textId: Int): String =
        context.getString(textId)

    override fun getInteger(id: Int): Int =
        context.resources.getInteger(id)

    override fun getColor(id: Int): Int =
        ContextCompat.getColor(context, id)

    override fun getDrawable(id: Int): Drawable? =
        ContextCompat.getDrawable(context, id)

}
