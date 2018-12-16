package com.shutterstock.imggetter.android.categories

import com.shutterstock.imggetter.android.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [CategoriesModule::class])
interface CategoriesComponent {
    fun inject(activity: CategoriesActivity)
}
