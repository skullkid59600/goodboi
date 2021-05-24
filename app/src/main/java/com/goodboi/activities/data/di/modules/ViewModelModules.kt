package com.goodboi.activities.data.di.modules

import com.goodboi.activities.data.Repository.AuthRepository
import com.goodboi.fragments.Home.HomeViewModel
import org.koin.dsl.module;

object ViewModelModules {

    val viewModels = module {
        fun createHomeViewModel(authRepository : AuthRepository){
            = HomeViewModel(authRepository)

            single{createHomeViewModel(get())}
        }
    }
}