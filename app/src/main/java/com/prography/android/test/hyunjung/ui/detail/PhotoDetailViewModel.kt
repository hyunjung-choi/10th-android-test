package com.prography.android.test.hyunjung.ui.detail

import androidx.lifecycle.*
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.data.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _photoDetail = MutableStateFlow<Photo?>(null)
    val photoDetail: StateFlow<Photo?> = _photoDetail

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val id: String? = savedStateHandle.get<String>("id")

    init {
        id?.let { loadPhotoDetail(it) }
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            _photoDetail.value?.let { photo ->
                val isBookmarked = repository.toggleBookmark(photo)
                _photoDetail.value = photo.copy(isBookmarked = repository.isBookmarked(photo.id))
            }
        }
    }

    fun refreshBookmarkStatus() {
        viewModelScope.launch {
            id?.let { id ->
                _photoDetail.value?.let { currentPhoto ->
                    try {
                        val isBookmarked = repository.isBookmarked(id)
                        if (currentPhoto.isBookmarked != isBookmarked) {
                            _photoDetail.value = currentPhoto.copy(isBookmarked = isBookmarked)
                        }
                    } catch (e: Exception) {
                        // 북마크 상태 확인 오류 처리
                    }
                }
            }
        }
    }

    fun loadPhotoDetail(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val photo = repository.getPhotoById(id)
                _photoDetail.value = photo
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
                _isLoading.value = false
            }
        }
    }
}