package com.example.featureauth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.EmptyEmailException
import com.example.core.model.Result
import com.example.featureauth.domain.model.EmailStatus
import com.example.featureauth.domain.model.NameStatus
import com.example.featureauth.domain.model.PasswordStatus
import com.example.featureauth.domain.usecase.ValidateUserEmailUseCase
import com.example.featureauth.domain.usecase.ValidateUserNameUseCase
import com.example.featureauth.domain.usecase.ValidateUserPasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val validateUserPasswordUseCase: ValidateUserPasswordUseCase,
    private val validateUserEmailUseCase: ValidateUserEmailUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _isValidUserEmail = MutableLiveData<EmailStatus>()
    val isValidUserEmail: LiveData<EmailStatus> = _isValidUserEmail

    private val _isValidUserPassword = MutableLiveData<PasswordStatus>()
    val isValidUserPassword: LiveData<PasswordStatus> = _isValidUserPassword

    private val _isValidUserName = MutableLiveData<NameStatus>()
    val isValidUserName: LiveData<NameStatus> = _isValidUserName

    private val _isAuthLogin = MutableLiveData<Boolean>()
    val isAuthLogin: LiveData<Boolean> = _isAuthLogin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun doLogin(email: String, password: String, name: String) {
        viewModelScope.launch(dispatcher) {

            when (val isValidEmail =
                validateUserEmailUseCase.call(email)) {
                is Result.Error -> {
                    val result = isValidEmail.exception
                    treatUserEmailResultsError(result)
                }
                is Result.Success -> {
                    _isValidUserEmail.postValue(EmailStatus.VALID)
                }
            }
            when (validateUserNameUseCase.call(name)) {
                is Result.Error -> {
                    _isValidUserName.postValue(NameStatus.INVALID)
                }
                is Result.Success -> {
                    _isValidUserName.postValue(NameStatus.VALID)
                }
            }

            when (val isValidPassword =
                validateUserPasswordUseCase.call(password)) {
                is Result.Error -> {
                    treatUserPasswordResultsError(isValidPassword)
                }
                is Result.Success -> {
                    _isValidUserPassword.postValue(PasswordStatus.VALID)
                }
            }

            val isAuthLogin = verifyIfLoginIsAuth()

            if (isAuthLogin) {
                _isLoading.postValue(true)
                delay(AUTH_DURATION)
                _isAuthLogin.postValue(true)
            } else {
                _isLoading.postValue(false)
                _isAuthLogin.postValue(false)
            }
        }
    }

    private fun treatUserPasswordResultsError(isValidPassword: Result.Error) {
        val result = isValidPassword.exception
        if (result is EmptyEmailException) {
            _isValidUserPassword.postValue(PasswordStatus.EMPTY)
        } else {
            _isValidUserPassword.postValue(PasswordStatus.INVALID)
        }
    }

    private fun verifyIfLoginIsAuth() = _isValidUserEmail.value == EmailStatus.VALID &&
            _isValidUserName.value == NameStatus.VALID &&
            _isValidUserPassword.value == PasswordStatus.VALID

    private fun treatUserEmailResultsError(result: Exception) {
        if (result is EmptyEmailException) {
            _isValidUserEmail.postValue(EmailStatus.EMPTY)
        } else {
            _isValidUserEmail.postValue(EmailStatus.INVALID)
        }
    }

    private companion object {
        const val AUTH_DURATION = 2500L
    }
}