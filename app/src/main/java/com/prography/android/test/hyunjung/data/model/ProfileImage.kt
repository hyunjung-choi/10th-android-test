package com.prography.android.test.hyunjung.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileImage(
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null
)