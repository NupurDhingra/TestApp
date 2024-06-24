package com.mstech.testapp.photos.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mstech.testapp.R
import com.mstech.testapp.basecomponent.BaseActivity
import com.mstech.testapp.databinding.ActivityMainBinding
import com.mstech.testapp.photos.constants.IntentExtrasConstants
import com.mstech.testapp.photos.domain.model.Photo
import com.mstech.testapp.photos.presentation.ui.adapters.PhotosAdapter
import com.mstech.testapp.photos.presentation.viewmodel.PhotosViewModel
import com.mstech.testapp.utils.showToastOrLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mPhotosViewModel by viewModels<PhotosViewModel>()

    private lateinit var photosAdapter: PhotosAdapter


    override fun initViews() {
        initPhotosAdapter()
        setObservers()
        mPhotosViewModel.fetchPhotos()
    }

    private fun initPhotosAdapter() {
        photosAdapter = PhotosAdapter(emptyList()) { photo ->
            launchDetailActivity(photo)
        }
        binding.recyclerView.adapter = photosAdapter
    }

    private fun setObservers() {
        if (::photosAdapter.isInitialized) {

            mPhotosViewModel.errorResponse.observe(this) { error ->
                showToastOrLog("Error", error)
            }

            mPhotosViewModel.photos.observe(this) { photos ->
                photos?.let {
                    photosAdapter.updatePhotosList(it)
                }
            }

            mPhotosViewModel.isLoading.observe(this) { isLoading ->
                if (isLoading) {
                    showProgressBar()
                } else {
                    hideProgressBar()
                }
            }
        }
    }

    private fun launchDetailActivity(photo: Photo) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(IntentExtrasConstants.EXTRAS_PHOTO_DATA, photo)
        startActivity(intent)
    }
}