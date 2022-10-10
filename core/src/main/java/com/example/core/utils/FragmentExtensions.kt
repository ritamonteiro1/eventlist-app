package com.example.core.utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

fun Fragment.setupToolbar(toolbar: MaterialToolbar) {
    (activity as? AppCompatActivity)?.apply {
        setSupportActionBar(toolbar)
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}