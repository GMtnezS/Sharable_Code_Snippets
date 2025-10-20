package com.example.m5cta.ui.screens

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m5cta.data.Photo
import com.example.m5cta.ui.components.ImageGrid
import com.example.m5cta.ui.components.PermissionRequestScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    photos: List<Photo>,
    isLoading: Boolean,
    onCameraClick: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mediaPermission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE
    val mediaPermState = rememberPermissionState(mediaPermission)

    // ask once, then refresh when granted
    LaunchedEffect(Unit) {
        if (!mediaPermState.status.isGranted) mediaPermState.launchPermissionRequest()
    }
    LaunchedEffect(mediaPermState.status.isGranted) {
        if (mediaPermState.status.isGranted) onRefresh()
    }

    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            onRefresh()
        }
    }

    if (!mediaPermState.status.isGranted) {
        PermissionRequestScreen(
            permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) "Photos" else "Storage",
            onRequestPermission = { mediaPermState.launchPermissionRequest() },
            modifier = modifier.fillMaxSize()
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Photo Gallery") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCameraClick) {
                Icon(Icons.Filled.PhotoCamera, contentDescription = "Open Camera")
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (!isLoading && photos.isEmpty()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No photos yet", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Use the camera to capture a photo or pick one from your device.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = onCameraClick) { Text("Open camera") }
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(onClick = {
                        pickImage.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) { Text("Pick from device") }
                }
            } else {
                ImageGrid(
                    photos = photos,
                    isLoading = isLoading,
                    onPhotoClick = { /* to interact with imgs would be here as triggered by click.. but leaving it out of the scope. let me know if required */ }
                )
            }
        }
    }
}
