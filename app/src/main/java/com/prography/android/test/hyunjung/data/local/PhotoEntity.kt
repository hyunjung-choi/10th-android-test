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
        user = this.user,
        isBookmarked = true
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

class PhotoTypeConverters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromTagsList(tags: List<Tag>): String {
        return json.encodeToString(tags)
    }

    @TypeConverter
    fun toTagsList(tagsString: String): List<Tag> {
        if (tagsString.isBlank()) return emptyList()
        return try {
            json.decodeFromString(tagsString)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromUser(user: User?): String {
        return if (user == null) "" else json.encodeToString(user)
    }

    @TypeConverter
    fun toUser(userString: String): User? {
        if (userString.isBlank()) return null
        return try {
            json.decodeFromString(userString)
        } catch (e: Exception) {
            null
        }
    }

    @TypeConverter
    fun fromUrls(urls: Urls?): String {
        return if (urls == null) "" else json.encodeToString(urls)
    }

    @TypeConverter
    fun toUrls(urlsString: String): Urls? {
        if (urlsString.isBlank()) return null
        return try {
            json.decodeFromString(urlsString)
        } catch (e: Exception) {
            null
        }
    }
}