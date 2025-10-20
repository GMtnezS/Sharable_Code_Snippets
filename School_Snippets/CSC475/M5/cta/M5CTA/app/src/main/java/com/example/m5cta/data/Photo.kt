package com.example.m5cta.data

import android.net.Uri

data class Photo(
    val id: String,
    val uri: Uri,
    val name: String,
    val dateModified: Long
)