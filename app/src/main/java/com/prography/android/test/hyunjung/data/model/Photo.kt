package com.prography.android.test.hyunjung.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: String,
    val title: String = "",
    val description: String? = null,
    @SerialName("alt_description")
    val altDescription: String? = null,
    val tags: List<Tag> = emptyList(),
    val urls: Urls,
    val user: User? = null
)