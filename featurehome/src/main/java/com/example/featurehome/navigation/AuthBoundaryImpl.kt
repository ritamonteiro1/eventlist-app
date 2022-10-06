package com.example.featurehome.navigation

import android.content.Context
import com.example.featurehome.presentation.HomeActivity
import com.example.navigation.AuthBoundary

class AuthBoundaryImpl : AuthBoundary {
    override fun navigateToHome(context: Context) {
        HomeActivity.launch(context)
    }
}