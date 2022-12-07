package com.kito.tlubook.domain.use_case

import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.domain.repository.PostRepository


class AddPost(
    private val repo: PostRepository
) {
    suspend operator fun invoke(
        post: Post
    ) = repo.addPost(post)
}