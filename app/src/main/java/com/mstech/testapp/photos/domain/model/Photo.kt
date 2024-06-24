package com.mstech.testapp.photos.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    @Json(name = "albumId")
    var albumId: Int? = null,

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "title")
    var title: String? = null,

    @Json(name = "url")
    var url: String? = null,

    @Json(name = "thumbnailUrl")
    var thumbnailUrl: String? = null
): Parcelable
