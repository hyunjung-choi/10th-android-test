package com.prography.android.test.hyunjung.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: String,
    @SerialName("alt_description")
    val altDescription: String?,
    val urls: Urls
)