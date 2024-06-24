package com.mstech.testapp.photos.domain.repository


import com.mstech.testapp.photos.data.remote.PhotosApiService
import com.mstech.testapp.photos.di.PhotosModule
import com.mstech.testapp.photos.domain.model.Photo
import retrofit2.Response
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    @PhotosModule.PhotosApi private val photosApi: PhotosApiService,
) {

    suspend fun fetchPhotos(): Response<List<Photo>> {
        return photosApi.fetchPhotos()
    }
}