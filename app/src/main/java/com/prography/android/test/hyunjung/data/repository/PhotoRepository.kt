package com.prography.android.test.hyunjung.data.repository

import android.util.Log
import com.prography.android.test.hyunjung.BuildConfig
import com.prography.android.test.hyunjung.data.local.PhotoDao
import com.prography.android.test.hyunjung.data.local.toDomain
import com.prography.android.test.hyunjung.data.local.toEntity
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.data.network.PhotoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PhotoRepository(
    private val apiService: PhotoApiService,
    private val photoDao: PhotoDao
) {
    private val loadedPhotoIds = mutableSetOf<String>()

    suspend fun fetchLatestPhotos(page: Int): List<Photo> {
        Log.d("HJ:::API_KEY", BuildConfig.UNSPLASH_ACCESS_KEY)
        return try {
            apiService.getLatestPhotos(page)
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getBookmarks(): Flow<List<Photo>> {
        return photoDao.getBookmarks().map { entities -> entities.map { it.toDomain() } }
    }

    suspend fun toggleBookmark(photo: Photo) {
        val exists = photoDao.getBookmarks().first().any { it.id == photo.id }
        if (exists) {
            photoDao.deleteBookmark(photo.toEntity())
        } else {
            photoDao.insertBookmark(photo.toEntity())
        }
    }
}