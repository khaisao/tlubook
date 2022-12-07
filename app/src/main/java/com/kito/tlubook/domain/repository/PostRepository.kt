package com.kito.tlubook.domain.repository

import com.kito.tlubook.core.UiState
import com.kito.tlubook.domain.model.Post
import kotlinx.coroutines.flow.Flow

typealias AddPostResponse = UiState<Boolean>
typealias Posts = List<Post>
typealias PostsResponse = UiState<Posts>
typealias UploadFileResponse = UiState<String>

interface PostRepository {
   fun getPostFromFireStore(): Flow<PostsResponse>
   suspend fun addPost(post: Post): AddPostResponse
   suspend fun uploadSingleFile(filePath: String): UploadFileResponse
}