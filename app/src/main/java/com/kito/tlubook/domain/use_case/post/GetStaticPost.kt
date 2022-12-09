package com.kito.tlubook.domain.use_case.post

import com.kito.tlubook.domain.repository.PostRepository


class GetStaticPost (
    private val repo: PostRepository
) {
    suspend operator fun invoke(id:String) = repo.getStaticPost(id)
}