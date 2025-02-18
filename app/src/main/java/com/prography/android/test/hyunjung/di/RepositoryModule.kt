package com.prography.android.test.hyunjung.di

import com.prography.android.test.hyunjung.data.local.PhotoDao
import com.prography.android.test.hyunjung.data.network.PhotoApiService
import com.prography.android.test.hyunjung.data.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotoRepository(
        apiService: PhotoApiService,
        photoDao: PhotoDao
    ): PhotoRepository {
        return PhotoRepository(apiService, photoDao)
    }
}