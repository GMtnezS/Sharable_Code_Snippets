package com.example.m5cta.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m5cta.data.Photo
import com.example.m5cta.data.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GalleryViewModel(private val repository: PhotoRepository) : ViewModel() {
    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Gallery)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    fun loadPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _photos.value = repository.loadPhotosFromDevice()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun switchToCamera() {
        _currentScreen.value = Screen.Camera
    }

    fun switchToGallery() {
        _currentScreen.value = Screen.Gallery
        loadPhotos()
    }

    enum class Screen {
        Gallery, Camera
    }
}