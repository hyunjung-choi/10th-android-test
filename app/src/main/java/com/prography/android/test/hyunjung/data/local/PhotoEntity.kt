package com.prography.android.test.hyunjung.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.data.model.Urls

@Entity(tableName = "bookmarks")
data class PhotoEntity(
    @PrimaryKey val id: String,
    val altDescription: String?,
    val imageUrl: String
)

fun PhotoEntity.toDomain(): Photo {
    return Photo(
        id = this.id,
        altDescription = this.altDescription,
        urls = Urls(
            raw = this.imageUrl,
            full = this.imageUrl,
            regular = this.imageUrl,
            small = this.imageUrl,
            thumb = this.imageUrl
        )
    )
}

fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = this.id,
        altDescription = this.altDescription,
        imageUrl = this.urls.regular
    )
}