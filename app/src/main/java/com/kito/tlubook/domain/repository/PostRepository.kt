package com.kito.tlubook.domain.repository

import com.kito.tlubook.core.UiState
import com.kito.tlubook.domain.model.Comment
import com.kito.tlubook.domain.model.Post
import kotlinx.coroutines.flow.Flow

typealias AddPostResponse = UiState<Boolean>
typealias AddCommentResponse = UiState<Boolean>
typealias Posts = List<Post>
typealias Comments = List<Comment>
typealias PostsResponse = UiState<Posts>
typealias CommentsResponse = UiState<Comments>
typealias UploadFileResponse = UiState<String>

interface PostRepository {
    fun getSnapshotPosts(): Flow<PostsResponse>
    fun getSnapshotComments(postId:String): Flow<CommentsResponse>
    suspend fun getStaticPost(id: String): Post?
    suspend fun addPost(post: Post): AddPostResponse
    suspend fun updatePost(post: Post): AddPostResponse
    suspend fun deletePost(post: Post): AddPostResponse
    suspend fun uploadSingleFile(filePath: String): UploadFileResponse
    suspend fun addComment(comment: Comment):AddCommentResponse
}