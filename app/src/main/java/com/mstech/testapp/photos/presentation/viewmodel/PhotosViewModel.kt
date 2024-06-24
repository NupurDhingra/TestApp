package com.mstech.testapp.photos.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mstech.testapp.photos.domain.model.Photo
import com.mstech.testapp.photos.domain.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
) : ViewModel() {


    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: MutableLiveData<String> = _errorResponse

    fun fetchPhotos() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = photosRepository.fetchPhotos()
                if (response.isSuccessful) {
                    _photos.postValue(response.body())
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorResponse.postValue(errorBody ?: "Unknown error occurred")
                }
            } catch (e: Exception) {
                errorResponse.postValue("Error occurred: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

}