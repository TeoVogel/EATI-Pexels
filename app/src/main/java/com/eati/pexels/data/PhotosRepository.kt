package com.eati.pexels.data

import com.eati.pexels.domain.PhotoExt

class PhotosRepository {

    private val pexelsApi = PexelsApi.create()

    suspend fun getPhotos(query: String): List<PhotoExt> = pexelsApi.getPhotos(query).photos.map {
        PhotoExt(
            id = it.id,
            width = it.width,
            height = it.height,
            url = it.url,
            photographer = it.photographer,
            photographerUrl = it.photographerUrl,
            photographerId = it.photographerId,
            avgColor = it.avgColor,
            liked = it.liked,
            alt = it.alt,
            sourceURL = it.src.large
        )
    }
}