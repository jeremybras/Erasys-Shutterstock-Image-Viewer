package com.shutterstock.imggetter.android

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import timber.log.Timber
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class MainModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideBackgroundExecutor(): Executor {
        return Executors.newFixedThreadPool(1)
    }

    @Singleton
    @Provides
    fun provideHandlerExecutor(): MainThreadExecutor {
        return MainThreadExecutor()
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideResources(): Resources {
        return context.resources
    }

    @Provides
    fun provideTimber(): Timber.Tree {
        return Timber.asTree()
    }
}
