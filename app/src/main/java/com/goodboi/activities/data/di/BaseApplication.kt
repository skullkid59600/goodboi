package com.goodboi.activities.data.di

import android.app.Application
import android.graphics.drawable.LevelListDrawable
import android.view.View
import com.goodboi.activities.data.di.modules.RepositoryModules
import com.goodboi.activities.data.di.modules.ServiceModules
import com.goodboi.activities.data.di.modules.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext( this@BaseApplication)
            modules(
                listOf(
                ServiceModules.services,
                RepositoryModules.repository,
                ViewModelModules.viewModels
                )
            )
            koin.createRootScope()
        }
    }

}