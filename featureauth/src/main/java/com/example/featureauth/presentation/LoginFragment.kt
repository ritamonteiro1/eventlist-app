package com.example.featureauth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.utils.Constants
import com.example.core.utils.createLoadingDialog
import com.example.featureauth.R
import com.example.featureauth.databinding.FragmentLoginBinding
import com.example.featureauth.domain.model.EmailStatus
import com.example.featureauth.domain.model.NameStatus
import com.example.featureauth.domain.model.PasswordStatus
import com.example.navigation.AuthBoundary
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private val boundary: AuthBoundary by inject()
    private val viewModel: LoginViewModel by viewModel()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loadingDialog by lazy { activity?.createLoadingDialog() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            viewModel.doLogin(
                email = binding.loginEmailEditText.text?.toString().orEmpty(),
                password = binding.loginPasswordEditText.text?.toString().orEmpty(),
                name = binding.loginNameEditText.text?.toString().orEmpty()
            )
        }
    }

    private fun setupObservers() {
        viewModel.isValidUserEmail.observe(viewLifecycleOwner) { emailStatus ->
            handleEmailField(emailStatus)
        }
        viewModel.isValidUserPassword.observe(viewLifecycleOwner) { passwordStatus ->
            handlePasswordField(passwordStatus)
        }
        viewModel.isValidUserName.observe(viewLifecycleOwner) { nameStatus ->
            handleNameField(nameStatus)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            handleIsLoading(isLoading)
        }
        viewModel.isAuthLogin.observe(viewLifecycleOwner) { isAuthLogin ->
            handleIsAuthLogin(isAuthLogin)
        }
    }

    private fun handleIsAuthLogin(isAuthLogin: Boolean) {
        if (isAuthLogin) moveToHomeActivity()
    }

    private fun moveToHomeActivity() {
        boundary.navigateToHome(requireContext())
    }

    private fun handleIsLoading(isLoading: Boolean) {
        if (isLoading) loadingDialog?.show()
        else loadingDialog?.dismiss()
    }

    private fun handleNameField(isValidName: NameStatus) {
        when (isValidName) {
            NameStatus.VALID -> {
                binding.loginNameTextInputLayout.error = Constants.EMPTY
            }
            NameStatus.INVALID -> {
                binding.loginNameTextInputLayout.error = getString(R.string.invalid_name)
            }
            NameStatus.EMPTY -> {
                binding.loginNameTextInputLayout.error = getString(R.string.fill_the_field)
            }
        }
    }

    private fun handlePasswordField(isValidPassword: PasswordStatus) {
        when (isValidPassword) {
            PasswordStatus.VALID -> {
                binding.loginPasswordTextInputLayout.error = Constants.EMPTY
            }
            PasswordStatus.EMPTY -> {
                binding.loginPasswordTextInputLayout.error = getString(R.string.fill_the_field)
            }
            PasswordStatus.INVALID -> {
                binding.loginPasswordTextInputLayout.error = getString(R.string.invalid_password)
            }
        }
    }

    private fun handleEmailField(isValidEmail: EmailStatus) {
        when (isValidEmail) {
            EmailStatus.VALID -> {
                binding.loginEmailTextInputLayout.error = Constants.EMPTY
            }
            EmailStatus.EMPTY -> {
                binding.loginEmailTextInputLayout.error =
                    getString(R.string.fill_the_field)
            }
            EmailStatus.INVALID -> {
                binding.loginEmailTextInputLayout.error =
                    getString(R.string.invalid_email)
            }
        }
    }

}