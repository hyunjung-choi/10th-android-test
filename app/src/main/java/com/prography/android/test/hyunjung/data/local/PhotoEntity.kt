package com.prography.android.test.hyunjung.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.data.model.Tag
import com.prography.android.test.hyunjung.data.model.Urls
import com.prography.android.test.hyunjung.data.model.User
import kotlinx.serialization.json.Json

@Entity(tableName = "bookmarks")
data class PhotoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val altDescription: String?,
    val tags: List<Tag>,
    val imageUrl: String,
    val user: User
)

fun PhotoEntity.toDomain(): Photo {
    return Photo(
        id = this.id,
        title = this.title,
        description = this.description,
        altDescription = this.altDescription,
        tags = this.tags,
        urls = Urls(regular = this.imageUrl),
        user = this.user
    )
}

fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        altDescription = this.altDescription,
        tags = this.tags,
        imageUrl = Urls(regular = this.urls.toString()).toString(),
        user = this.user
    )
}

class Converters {
    @TypeConverter
    fun fromTagList(tags: List<Tag>): String {
        return Json.encodeToString(tags)
    }

    @TypeConverter
    fun toTagList(tagsString: String): List<Tag> {
        return Json.decodeFromString(tagsString)
    }

    @TypeConverter
    fun fromUser(username: User): String {
        return Json.encodeToString(username)
    }

    @TypeConverter
    fun toUser(username: String): User {
        return Json.decodeFromString(username)
    }
}