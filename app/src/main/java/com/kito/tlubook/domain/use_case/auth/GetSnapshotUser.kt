package com.kito.tlubook.domain.use_case.auth

import com.kito.tlubook.domain.repository.AuthRepository
import com.kito.tlubook.domain.repository.PostRepository


class GetSnapshotUser(
    private val repo: AuthRepository
) {
    operator fun invoke(userId: String) = repo.getSnapshotCurrentUser(userId)
}