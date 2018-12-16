package com.shutterstock.imggetter.android.categories

import com.octo.workerdecorator.WorkerDecoration
import com.octo.workerdecorator.WorkerDecorator
import com.octo.workerdecorator.WorkerDecorator.decorate
import com.shutterstock.imggetter.android.FeatureScope
import com.shutterstock.imggetter.android.MainThreadExecutor
import com.shutterstock.imggetter.core.categories.CategoriesInteractor
import com.shutterstock.imggetter.core.categories.CategoriesRepository
import com.shutterstock.imggetter.repository.categories.CategoriesApiRepository
import com.shutterstock.imggetter.repository.categories.CategoriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.Executor

@Module
class CategoriesModule {

    @Provides
    fun provideCategoriesViewModel(
        controller: CategoriesController,
        decorator: WorkerDecoration<CategoriesView>
    ): CategoriesViewModel {
        val vm = CategoriesViewModel(controller)
        decorator.setDecorated(vm)
        return vm
    }

    @Provides
    fun provideCategoriesController(
        interactor: CategoriesInteractor,
        executor: Executor
    ): CategoriesController = decorate(CategoriesControllerImpl(interactor), executor)

    @Provides
    fun provideCategoriesInteractor(
        presenter: CategoriesPresenter,
        repository: CategoriesRepository
    ): CategoriesInteractor = CategoriesInteractor(presenter, repository)


    @Provides
    fun provideCategoriesPresenter(
        userView: CategoriesView
    ): CategoriesPresenter = CategoriesPresenter(userView)

    @Provides
    @FeatureScope
    fun provideCategoriesViewDecorated(executor: MainThreadExecutor): WorkerDecoration<CategoriesView> =
        WorkerDecorator.decorate(CategoriesView::class.java, executor)

    @Provides
    fun provideCategoriesView(decorator: WorkerDecoration<CategoriesView>): CategoriesView = decorator.asType()

    @Provides
    fun provideService(retrofit: Retrofit): CategoriesService = retrofit.create(CategoriesService::class.java)

    @Provides
    fun provideCategoriesRepository(
        service: CategoriesService
    ): CategoriesRepository = CategoriesApiRepository(
        service
    )
}
