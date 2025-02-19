package com.prography.android.test.hyunjung.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.data.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos.asStateFlow()

    val bookmarks = repository.getBookmarks().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private var currentPage = 1

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        viewModelScope.launch {
            _photos.value += repository.fetchLatestPhotos(currentPage)
            currentPage++
        }
    }

    fun toggleBookmark(photo: Photo) {
        viewModelScope.launch {
            repository.toggleBookmark(photo)
        }
    }
}