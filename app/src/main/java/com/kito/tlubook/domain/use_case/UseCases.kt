package com.kito.tlubook.domain.use_case

data class UseCases (
    val getPosts: GetPosts,
    val addPost: AddPost,
    val uploadSingleFile: UploadSingleFile
)