package com.goodboi.activities.data.di.modules

import com.goodboi.activities.data.Repository.AuthRepository
import com.goodboi.fragments.Home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module;

object ViewModelModules {

    val viewModels = module {
        fun createHomeViewModel(authRepository : AuthRepository): HomeViewModel {
             return HomeViewModel()

        }
        viewModel{createHomeViewModel(get())}
    }
}