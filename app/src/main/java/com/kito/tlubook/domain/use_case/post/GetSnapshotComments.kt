package com.kito.tlubook.domain.use_case.post

import com.kito.tlubook.domain.repository.PostRepository


class GetSnapshotComments(
    private val repo: PostRepository
) {
    operator fun invoke(postId: String) = repo.getSnapshotComments(postId)
}