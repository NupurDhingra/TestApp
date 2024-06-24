package com.mstech.testapp.photos.di

import com.mstech.testapp.network.di.NetworkModule
import com.mstech.testapp.photos.data.remote.PhotosApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object PhotosModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PhotosApi

    @Provides
    @PhotosApi
    fun providesPhotosApi(@NetworkModule.NetworkInterceptorOkHttpClient retrofit: Retrofit)
            : PhotosApiService = retrofit.create(PhotosApiService::class.java)

}