package com.kito.tlubook.domain.use_case.post

import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.domain.repository.PostRepository


class DeletePost(
    private val repo: PostRepository
) {
    suspend operator fun invoke(
        post: Post
    ) = repo.deletePost(post)
}