package com.example.core.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

fun Fragment.setupToolbar(toolbar: MaterialToolbar){
    (activity as? AppCompatActivity)?.apply {
        setSupportActionBar(toolbar)
    }
}