package com.adorastudios.graphqlevaluationassignment.domain

import android.app.Application
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Toaster(private val application: Application) {
    private var toast: Toast? = null

    suspend fun show(message: String) = withContext(Dispatchers.Main) {
        toast?.cancel()

        Toast.makeText(application, message, Toast.LENGTH_SHORT).run {
            toast = this
            show()
        }
    }

    suspend fun show(message: Int) = withContext(Dispatchers.Main) {
        toast?.cancel()

        Toast.makeText(application, message, Toast.LENGTH_SHORT).run {
            toast = this
            show()
        }
    }
}
