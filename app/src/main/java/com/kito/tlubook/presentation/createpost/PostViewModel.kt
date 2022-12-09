package com.kito.tlubook.presentation.createpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kito.tlubook.core.UiState
import com.kito.tlubook.domain.model.Comment
import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.domain.repository.*
import com.kito.tlubook.domain.use_case.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postUseCase: PostUseCase
) : ViewModel() {

    init {
        getPosts()
    }

    private val _addPostResponse = MutableStateFlow<AddPostResponse>(UiState.Loading)
    val addPostResponse= _addPostResponse.asStateFlow()

    private var _addCommentResponse = MutableStateFlow<AddCommentResponse>(UiState.Loading)
    val addCommentResponse= _addCommentResponse.asStateFlow()

    private val _uploadFileResponse = MutableStateFlow<UploadFileResponse>(UiState.Loading)
    private val uploadFileResponse = _uploadFileResponse.asStateFlow()

    private val _postsResponse = MutableStateFlow<PostsResponse>(UiState.Loading)
    val postsResponse= _postsResponse.asStateFlow()

    private val _commentsResponse = MutableStateFlow<CommentsResponse>(UiState.Loading)
    val commentsResponse= _commentsResponse.asStateFlow()


    private fun getPosts() = viewModelScope.launch {
        postUseCase.getSnapshotPosts().collect { response ->
            _postsResponse.value = response
        }
    }

     fun getComments(postId:String) = viewModelScope.launch {
        postUseCase.getSnapshotComments(postId).collect { response ->
            _commentsResponse.value = response
        }
    }

    fun addPost(post: Post) = viewModelScope.launch {
        _addPostResponse.value = UiState.Loading
        uploadSingleFile(post.linkImage)
        uploadFileResponse.collect { state ->
            when (state) {
                is UiState.Success -> {
                    val linkFileFromFirebase = state.data
                    post.linkImage = linkFileFromFirebase
                    _addPostResponse.value = postUseCase.addPost(post)
                }
                else -> {
                    _addPostResponse.value = UiState.Failure("Error")
                }
            }
            this.coroutineContext.job.cancel()
        }
    }

    fun addComment(comment: Comment) = viewModelScope.launch {
       _addCommentResponse.value = postUseCase.addComment(comment)
    }

    private suspend fun uploadSingleFile(filePath: String) {
        _uploadFileResponse.value = UiState.Loading
        _uploadFileResponse.value = postUseCase.uploadSingleFile(filePath)
    }


}