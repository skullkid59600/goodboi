package com.goodboi.activities.data.di.modules

import com.goodboi.activities.data.dataSource.remote.firebase.FirebaseAuthService
import org.koin.dsl.module

object ServiceModules {

    val services = module {
        fun createFirebaseAuthService()
                = FirebaseAuthService()
        single { createFirebaseAuthService() }
    }
}