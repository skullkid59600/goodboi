package com.goodboi.activities.data.di.modules

import com.goodboi.activities.data.Repository.AuthRepository
import com.goodboi.fragments.viewModel.LoginViewModel
import org.koin.dsl.module;

object ViewModelModules {

    val viewModels = module {
        fun createLoginViewModel(authRepository : AuthRepository)
                = LoginViewModel(authRepository)
        single { createLoginViewModel(get()) }
    }
}