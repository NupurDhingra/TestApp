package com.mstech.testapp.utils


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mstech.testapp.BuildConfig
import com.mstech.testapp.R

fun Context.showToastOrLog(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    Log.d(tag, message)
}

fun logLifecycle(tag: String = "", message: String) {
    if (BuildConfig.DEBUG)
        Log.d(tag, "$tag: $message Lifecycle")
}

fun log(tag: String = "", message: String) {
    if (BuildConfig.DEBUG)
        Log.d(tag, "$tag: $message")
}

fun logError(tag: String = "", message: String) {
    if (BuildConfig.DEBUG)
        Log.e(tag, "$tag: $message")
}

fun logError(tag: String = "", message: String, e: Throwable) {
    if (BuildConfig.DEBUG)
        Log.e(tag, "$tag: $message", e)
}

fun getProgressDialog(context: Context?): AlertDialog? {
    if (context == null) return null
    return MaterialAlertDialogBuilder(context)
        .setView(R.layout.layout_progess_bar)
        .setBackground(ColorDrawable(Color.TRANSPARENT))
        .setCancelable(false)
        .create()
}

