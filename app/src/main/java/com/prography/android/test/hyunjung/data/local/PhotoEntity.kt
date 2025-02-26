package com.prography.android.test.hyunjung.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.data.model.Tag
import com.prography.android.test.hyunjung.data.model.Urls
import com.prography.android.test.hyunjung.data.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json

@Entity(tableName = "bookmarks")
@TypeConverters(PhotoTypeConverters::class)
data class PhotoEntity(
    @PrimaryKey val id: String,
    val title: String = "",
    val description: String? = null,
    @SerialName("alt_description")
    val altDescription: String? = null,
    val tags: List<Tag> = emptyList(),
    val urls: Urls? = null,
    val user: User? = null
)

fun PhotoEntity.toDomain(): Photo {
    return Photo(
        id = this.id,
        title = this.title,
        description = this.description,
        altDescription = this.altDescription,
        tags = this.tags,
        urls = Urls(this.urls?.regular ?: ""),
        user = this.user
    )
}

@TypeConverters
fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        altDescription = this.altDescription,
        tags = this.tags,
        urls = this.urls,
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