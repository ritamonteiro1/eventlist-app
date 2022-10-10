package com.example.di

import com.example.featureauth.data.repository.UserRepositoryImpl
import com.example.featureauth.domain.repository.UserRepository
import com.example.featureauth.domain.usecase.ValidateUserEmailUseCase
import com.example.featureauth.domain.usecase.ValidateUserEmailUseCaseImpl
import com.example.featureauth.domain.usecase.ValidateUserNameUseCase
import com.example.featureauth.domain.usecase.ValidateUserNameUseCaseImpl
import com.example.featureauth.domain.usecase.ValidateUserPasswordUseCase
import com.example.featureauth.domain.usecase.ValidateUserPasswordUseCaseImpl
import com.example.featureauth.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<ValidateUserEmailUseCase> {
        ValidateUserEmailUseCaseImpl(get())
    }

    single<ValidateUserNameUseCase> {
        ValidateUserNameUseCaseImpl(get())
    }

    single<ValidateUserPasswordUseCase> {
        ValidateUserPasswordUseCaseImpl()
    }

    viewModel { LoginViewModel(get(), get(), get()) }
}