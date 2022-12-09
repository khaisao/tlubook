package com.kito.tlubook.domain.use_case.post

import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.domain.repository.PostRepository


class UpdatePost(
    private val repo: PostRepository
) {
    suspend operator fun invoke(
        post: Post
    ) = repo.updatePost(post)
}