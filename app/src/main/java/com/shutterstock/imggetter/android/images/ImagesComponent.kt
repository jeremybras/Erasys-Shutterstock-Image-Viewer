package com.shutterstock.imggetter.android.images

import com.shutterstock.imggetter.android.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [ImagesModule::class])
interface ImagesComponent {
    fun inject(activity: ImagesActivity)
}
