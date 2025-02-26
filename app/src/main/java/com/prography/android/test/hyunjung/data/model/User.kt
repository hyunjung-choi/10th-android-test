package com.prography.android.test.hyunjung.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val username: String,
    val name: String? = null,
    @SerialName("profile_image")
    val profileImage: ProfileImage? = null
)