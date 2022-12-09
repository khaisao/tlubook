package com.kito.tlubook.domain.use_case

import com.kito.tlubook.domain.use_case.post.*

data class PostUseCase(
    val getSnapshotPosts: GetSnapshotPosts,
    val getSnapshotComments: GetSnapshotComments,
    val addPost: AddPost,
    val updatePost: UpdatePost,
    val deletePost: DeletePost,
    val uploadSingleFile: UploadSingleFile,
    val addComment: AddComment
)