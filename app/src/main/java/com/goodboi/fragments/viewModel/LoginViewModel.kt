package com.goodboi.fragments.viewModel

import androidx.lifecycle.ViewModel
import com.goodboi.activities.data.Repository.AuthRepository

class LoginViewModel (val authRepository: AuthRepository): ViewModel() {
    fun signIn(email: String, password: String) = authRepository.signIn(email, password)
    fun signUp(email: String, password: String) = authRepository.signUp(email, password)
}