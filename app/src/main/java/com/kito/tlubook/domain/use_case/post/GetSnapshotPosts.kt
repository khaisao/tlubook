package com.kito.tlubook.domain.use_case.post

import com.kito.tlubook.domain.repository.PostRepository


class GetSnapshotPosts (
    private val repo: PostRepository
) {
    operator fun invoke() = repo.getSnapshotPosts()
}