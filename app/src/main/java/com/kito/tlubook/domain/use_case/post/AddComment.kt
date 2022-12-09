package com.kito.tlubook.domain.use_case.post

import com.kito.tlubook.domain.model.Comment
import com.kito.tlubook.domain.repository.PostRepository


class AddComment(
    private val repo: PostRepository
) {
    suspend operator fun invoke(
        comment: Comment
    ) = repo.addComment(comment)
}