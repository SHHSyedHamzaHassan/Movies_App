package com.yassir.moviesapp.utils

import android.app.AlertDialog
import android.content.Context
import com.yassir.moviesapp.R

class CommonAlert {
    companion object {
        fun setAlert(
            context: Context,
            message: String
        ) {
            val alert = AlertDialog.Builder(context)
            alert.setMessage(message)
            alert.setPositiveButton(
                context.resources.getString(R.string.error_alert_button_ok),
                null
            )
            alert.show()
        }
    }
}