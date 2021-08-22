package com.bd.mascogroup.automation.utils.extension

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ProgressBar

object ContextExtension {
    fun Context.showLoadingDialog(): ProgressBar {
        val progressDialog = ProgressBar(this)
        progressDialog.visibility = View.VISIBLE
        progressDialog.background = ColorDrawable(Color.TRANSPARENT)
        progressDialog.isIndeterminate = true
        return progressDialog
    }
}