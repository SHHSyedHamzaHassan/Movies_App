package com.yassir.moviesapp.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.yassir.moviesapp.R

class CommonLoader {
    companion object {
        lateinit var dialog: Dialog
        fun showLoader(act: Activity) {
            dialog = Dialog(act, R.style.TranslucentDialog)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.loader_dialog)
            dialog.show()
        }

        fun dismissLoader() {
            if (dialog.isShowing)
                dialog.dismiss()
        }
    }
}