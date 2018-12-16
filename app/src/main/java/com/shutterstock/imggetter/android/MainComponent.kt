package com.shutterstock.imggetter.android

import com.shutterstock.imggetter.android.categories.CategoriesComponent
import com.shutterstock.imggetter.android.categories.CategoriesModule
import com.shutterstock.imggetter.android.images.ImagesComponent
import com.shutterstock.imggetter.android.images.ImagesModule
import dagger.Component
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, NetworkModule::class])
interface MainComponent {
    fun plus(module: CategoriesModule): CategoriesComponent
    fun plus(module: ImagesModule): ImagesComponent
    fun moshiConverter(): MoshiConverterFactory // For integration tests
}
