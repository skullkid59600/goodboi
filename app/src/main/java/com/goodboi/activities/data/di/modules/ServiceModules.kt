package com.goodboi.activities.data.di.modules

import com.goodboi.activities.data.dataSource.remote.FirebaseAuthService
import org.koin.dsl.module

object ServiceModules {

    val services = module {
        fun createFirebaseAuthService() = FirebaseAuthService()
    }
}