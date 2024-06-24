package com.mstech.testapp.photos.presentation.ui

import android.view.View
import com.mstech.testapp.R
import com.mstech.testapp.basecomponent.BaseActivity
import com.mstech.testapp.databinding.ActivityDetailBinding
import com.mstech.testapp.photos.constants.IntentExtrasConstants
import com.mstech.testapp.photos.domain.model.Photo

class DetailsActivity :
    BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate), View.OnClickListener {

    override fun initViews() {
        initClickListeners()
        receiveExtras()
    }

    private fun receiveExtras() {
        val photo = intent.getParcelableExtra<Photo>(IntentExtrasConstants.EXTRAS_PHOTO_DATA)

        photo?.let {
            binding.photoData = it
            binding.executePendingBindings()
        }
    }

    private fun initClickListeners() {
        binding.ivBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}