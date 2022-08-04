package com.example.ineedpizzabeer.utils

import android.app.AlertDialog
import android.content.Context

class CustomDialog(context: Context) : AlertDialog.Builder(context) {

    fun show(title: String, message: String) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)

        alertDialog.show()

    }
}