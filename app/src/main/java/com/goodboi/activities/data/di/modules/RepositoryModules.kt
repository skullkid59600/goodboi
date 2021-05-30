package com.goodboi.activities.data.di.modules

import com.goodboi.activities.data.Repository.AuthRepository
import com.goodboi.activities.data.dataSource.remote.firebase.FirebaseAuthService
import org.koin.dsl.module

object RepositoryModules {

    val repository = module{
        fun createAuthRepository(firebaseAuthService: FirebaseAuthService)
                = AuthRepository(firebaseAuthService)
        single {createAuthRepository(get())}
    }
}