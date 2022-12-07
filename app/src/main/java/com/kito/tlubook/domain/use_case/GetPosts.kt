package com.kito.tlubook.domain.use_case

import com.kito.tlubook.domain.repository.PostRepository


class GetPosts (
    private val repo: PostRepository
) {
    operator fun invoke() = repo.getPostFromFireStore()
}