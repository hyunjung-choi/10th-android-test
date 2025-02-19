package com.prography.android.test.hyunjung.di

import android.content.Context
import androidx.room.Room
import com.prography.android.test.hyunjung.data.local.PhotoDao
import com.prography.android.test.hyunjung.data.local.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PhotoDatabase {
        return Room.databaseBuilder(
            context,
            PhotoDatabase::class.java,
            "photo_db"
        ).build()
    }

    @Provides
    fun providePhotoDao(db: PhotoDatabase): PhotoDao {
        return db.photoDao()
    }
}