package com.example.m5cta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState

import com.example.m5cta.data.PhotoRepository
import com.example.m5cta.ui.screens.CameraScreen
import com.example.m5cta.ui.screens.GalleryScreen
import com.example.m5cta.ui.theme.PhotoGalleryTheme
import com.example.m5cta.viewmodel.GalleryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = PhotoRepository(this)

        setContent {
            PhotoGalleryTheme {
                val vm = viewModel<GalleryViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return GalleryViewModel(repository) as T
                        }
                    }
                )

                val photos by vm.photos.collectAsState()
                val isLoading by vm.isLoading.collectAsState()
                val currentScreen by vm.currentScreen.collectAsState()

                when (currentScreen) {
                    GalleryViewModel.Screen.Gallery -> {
                        GalleryScreen(
                            photos = photos,
                            isLoading = isLoading,
                            onCameraClick = { vm.switchToCamera() },
                            onRefresh = { vm.loadPhotos() }
                        )
                    }
                    GalleryViewModel.Screen.Camera -> {
                        CameraScreen(
                            onBack = { vm.switchToGallery() },
                            onCaptured = { _ ->
                                // After saving to MediaStore, return and refresh the grid
                                vm.switchToGallery()
                                vm.loadPhotos()
                            }
                        )
                    }
                }
            }
        }
    }
}
