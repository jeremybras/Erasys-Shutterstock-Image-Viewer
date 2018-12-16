package com.shutterstock.imggetter.android.images

import com.octo.workerdecorator.WorkerDecoration
import com.octo.workerdecorator.WorkerDecorator
import com.octo.workerdecorator.WorkerDecorator.decorate
import com.shutterstock.imggetter.android.FeatureScope
import com.shutterstock.imggetter.android.MainThreadExecutor
import com.shutterstock.imggetter.core.images.ImagesInteractor
import com.shutterstock.imggetter.core.images.ImagesRepository
import com.shutterstock.imggetter.repository.images.ImagesApiRepository
import com.shutterstock.imggetter.repository.images.ImagesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.Executor

@Module
class ImagesModule {

    @Provides
    fun provideImagesViewModel(
        controller: ImagesController,
        decorator: WorkerDecoration<ImagesView>
    ): ImagesViewModel {
        val vm = ImagesViewModel(controller)
        decorator.setDecorated(vm)
        return vm
    }

    @Provides
    fun provideImagesController(
        interactor: ImagesInteractor,
        executor: Executor
    ): ImagesController = decorate(ImagesControllerImpl(interactor), executor)

    @Provides
    fun provideImagesInteractor(
        presenter: ImagesPresenter,
        repository: ImagesRepository
    ): ImagesInteractor = ImagesInteractor(presenter, repository)


    @Provides
    fun provideImagesPresenter(
        userView: ImagesView
    ): ImagesPresenter = ImagesPresenter(userView)

    @Provides
    @FeatureScope
    fun provideImagesViewDecorated(executor: MainThreadExecutor): WorkerDecoration<ImagesView> =
        WorkerDecorator.decorate(ImagesView::class.java, executor)

    @Provides
    fun provideImagesView(decorator: WorkerDecoration<ImagesView>): ImagesView = decorator.asType()

    @Provides
    fun provideService(retrofit: Retrofit): ImagesService = retrofit.create(
        ImagesService::class.java
    )

    @Provides
    fun provideImagesRepository(
        service: ImagesService
    ): ImagesRepository = ImagesApiRepository(service)
}
