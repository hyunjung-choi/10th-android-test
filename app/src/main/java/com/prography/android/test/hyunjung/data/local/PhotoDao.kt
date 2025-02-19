package com.prography.android.test.hyunjung.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Flow<List<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(photo: PhotoEntity)

    @Delete
    suspend fun deleteBookmark(photo: PhotoEntity)
}