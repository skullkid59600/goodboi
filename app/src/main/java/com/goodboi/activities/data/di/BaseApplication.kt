package com.goodboi.activities.data.di

import android.app.Application
import android.graphics.drawable.LevelListDrawable
import android.view.View
import com.goodboi.activities.data.di.modules.ServiceModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.NONE)
            androidContext( this@BaseApplication)
            module(listOf(
                ServiceModules.services
                RepositoryModules.
            ))
        }
    }

}