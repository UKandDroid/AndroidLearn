package com.example.network.model

data class PhotosResponse(
    val success: Boolean,
    val message:String,
    val offset: Int,
    val limit: Int,
    val photos: List<Photo>)

data class ListPhotos(val listPhotos: List<Photo>)

data class Photo(
    val title: String,
    val url: String,
    val description: String,
)

