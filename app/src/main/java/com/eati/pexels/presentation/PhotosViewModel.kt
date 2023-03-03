package com.eati.pexels.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eati.pexels.data.PhotosRepository
import com.eati.pexels.domain.PhotoExt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PhotosViewModel(private val repository: PhotosRepository) : ViewModel() {

    val photosFlow = MutableStateFlow<List<PhotoExt>>(listOf())

    fun updateResults(query: String) {
        viewModelScope.launch {
            val results = if (query.isBlank())
                emptyList<PhotoExt>()
            else
                repository.getPhotos(query)

            photosFlow.emit(results)
        }
    }
}