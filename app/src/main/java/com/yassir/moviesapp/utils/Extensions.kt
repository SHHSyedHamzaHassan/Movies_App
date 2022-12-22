package com.yassir.moviesapp.utils

import android.os.Build
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yassir.moviesapp.R
import com.yassir.moviesapp.utils.Constants.DataProviderEndPoints.Companion.IMAGE_URL

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, url: String?) {
    url?.let {
        val options =
            RequestOptions.placeholderOf(R.drawable.loading).error(R.drawable.error)
        Glide.with(view).setDefaultRequestOptions(options).load(IMAGE_URL + url)
            .into(view)
    }
}

fun setLoaderToFullScreen(dialogFragment: DialogFragment) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        dialogFragment.setStyle(
            DialogFragment.STYLE_NO_TITLE,
            android.R.style.Theme_Material_Light_NoActionBar_Fullscreen
        )
    else
        dialogFragment.setStyle(
            DialogFragment.STYLE_NO_TITLE,
            android.R.style.Theme_DeviceDefault_Light_NoActionBar
        )

}
