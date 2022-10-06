package com.example.featureauth.presentation

import androidx.lifecycle.ViewModel
import com.example.featureauth.domain.usecase.ValidateUserEmailUseCase
import com.example.featureauth.domain.usecase.ValidateUserNameUseCase
import com.example.featureauth.domain.usecase.ValidateUserPasswordUseCase

class LoginViewModel(
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val validateUserPasswordUseCase: ValidateUserPasswordUseCase,
    private val validateUserEmailUseCase: ValidateUserEmailUseCase,
) : ViewModel() {
}