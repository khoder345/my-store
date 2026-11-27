package com.example.store.data

import kotlinx.serialization.Serializable

@Serializable
data class Catalog(
    val schemaVersion: Int = 1,
    val updatedAt: String = "",
    val categories: List<Category> = emptyList(),
    val apps: List<StoreApp> = emptyList()
)

@Serializable
data class Category(
    val id: String,
    val name: String
)

@Serializable
data class ApkInfo(
    val type: String = "apk",
    val url: String = "",
    val sha256: String = "",
    val signatureSha256: String = ""
)

@Serializable
data class StoreApp(
    val packageName: String,
    val name: String,
    val developer: String = "",
    val category: String = "apps",
    val versionCode: Long = 0,
    val versionName: String = "",
    val minSdk: Int = 24,
    val sizeBytes: Long = 0,
    val iconUrl: String = "",
    val screenshots: List<String> = emptyList(),
    val description: String = "",
    val whatsNew: String = "",
    val updatedAt: String = "",
    val apk: ApkInfo = ApkInfo()
)
