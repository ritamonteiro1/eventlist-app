package com.example.di

import com.example.core.utils.UseCase
import com.example.featureauth.domain.usecase.ValidateUserEmailUseCase
import com.example.featureauth.domain.usecase.ValidateUserNameUseCase
import com.example.featureauth.domain.usecase.ValidateUserPasswordUseCase
import com.example.featureauth.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<UseCase<ValidateUserEmailUseCase.Params, Unit>> {
        ValidateUserEmailUseCase()
    }

    single<UseCase<ValidateUserNameUseCase.Params, Unit>> {
        ValidateUserNameUseCase()
    }

    single<UseCase<ValidateUserPasswordUseCase.Params, Unit>> {
        ValidateUserPasswordUseCase()
    }

    viewModel { LoginViewModel(get(), get(), get()) }
}