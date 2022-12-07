package com.kito.tlubook.presentation.createpost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kito.tlubook.core.UiState
import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.domain.repository.AddPostResponse
import com.kito.tlubook.domain.repository.Posts
import com.kito.tlubook.domain.repository.PostsResponse
import com.kito.tlubook.domain.repository.UploadFileResponse
import com.kito.tlubook.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    init {
        getPosts()
    }

    private val _addPostResponse = MutableStateFlow<AddPostResponse>(UiState.Failure(""))
    val addPostResponse: StateFlow<UiState<Boolean>> = _addPostResponse

    private val _uploadFileResponse = MutableStateFlow<UploadFileResponse>(UiState.Failure(""))
    private val uploadFileResponse: StateFlow<UiState<String>> = _uploadFileResponse

    private val _postsResponse = MutableStateFlow<PostsResponse>(UiState.Loading)
    val postsResponse: StateFlow<UiState<Posts>> = _postsResponse


    fun addPost(post: Post) = viewModelScope.launch {
        _addPostResponse.value = UiState.Loading
        uploadSingleFile(post.linkImage)
        uploadFileResponse.collect { state ->
            when (state) {
                is UiState.Success -> {
                    val linkFileFromFirebase = state.data
                    post.linkImage = linkFileFromFirebase
                    _addPostResponse.value = useCases.addPost(post)
                }
                else -> {
                    _addPostResponse.value = UiState.Failure("Error")
                }
            }
            this.coroutineContext.job.cancel()
        }
    }

    private fun getPosts() = viewModelScope.launch {
        useCases.getPosts().collect { response ->
            _postsResponse.value = response
        }
    }

    private suspend fun uploadSingleFile(filePath: String) {
        _uploadFileResponse.value = UiState.Loading
        _uploadFileResponse.value = useCases.uploadSingleFile(filePath)
    }


}