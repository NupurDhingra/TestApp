package com.mstech.testapp.photos.data.remote

import com.mstech.testapp.photos.domain.model.Photo
import com.mstech.testapp.photos.constants.ApiConstants.FETCH_PHOTOS
import retrofit2.Response

import retrofit2.http.*


interface PhotosApiService {


    @GET(FETCH_PHOTOS)
    suspend fun fetchPhotos(): Response<List<Photo>>

}