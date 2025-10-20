package com.example.m5cta.ui.screens

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.m5cta.ui.components.PermissionRequestScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    onBack: () -> Unit,
    onCaptured: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val camPerm = rememberPermissionState(Manifest.permission.CAMERA)
    if (!camPerm.status.isGranted) {
        PermissionRequestScreen(
            permission = "Camera",
            onRequestPermission = { camPerm.launchPermissionRequest() },
            modifier = modifier.fillMaxSize()
        )
        return
    }
    CameraContent(onBack = onBack, onCaptured = onCaptured, modifier = modifier)
}

@Composable
private fun CameraContent(
    onBack: () -> Unit,
    onCaptured: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Keep a reference to ImageCapture
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }

     fun createOutputOptions(context: Context): ImageCapture.OutputFileOptions {
        val name = "IMG_${System.currentTimeMillis()}.jpg"
        val collection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            else
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/M5CTA")
            }
        }

        return ImageCapture.OutputFileOptions
            .Builder(context.contentResolver, collection, values)
            .build()
    }


    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()

                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(surfaceProvider)
                        }

                        imageCapture = ImageCapture.Builder()
                            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                            .build()

                        val selector = CameraSelector.DEFAULT_BACK_CAMERA
                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                selector,
                                preview,
                                imageCapture
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }, ContextCompat.getMainExecutor(ctx))
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onBack) { Text("Back") }
            Spacer(Modifier.width(16.dp))
            Button(
                onClick = {
                    val options = createOutputOptions(context)
                    imageCapture?.takePicture(
                        options,
                        ContextCompat.getMainExecutor(context),
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                output.savedUri?.let { onCaptured(it) } ?: onBack()
                            }
                            override fun onError(exception: ImageCaptureException) {
                                exception.printStackTrace()
                                onBack()
                            }
                        }
                    )
                }
            ) { Text("Shutter") }
        }
    }
}
