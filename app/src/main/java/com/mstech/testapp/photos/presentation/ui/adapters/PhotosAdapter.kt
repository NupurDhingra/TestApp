package com.mstech.testapp.photos.presentation.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mstech.testapp.R
import com.mstech.testapp.databinding.RowPhotoItemBinding
import com.mstech.testapp.photos.domain.model.Photo
import com.squareup.picasso.Picasso

class PhotosAdapter(
    private var photosList: List<Photo>,
    private val onItemClick: (Photo) -> Unit
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {


    fun updatePhotosList(list: List<Photo>) {
        this.photosList = list
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: RowPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(photo: Photo) {
            binding.photo = photo
            binding.executePendingBindings()
            binding.root.setOnClickListener { onItemClick(photo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapter.ViewHolder {
        val binding =
            RowPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosAdapter.ViewHolder, position: Int) {
        holder.bindData(photosList[position])
    }

    override fun getItemCount(): Int = photosList.size
}
