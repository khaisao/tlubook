package com.kito.tlubook.domain.use_case.post

import android.net.Uri
import com.kito.tlubook.domain.repository.PostRepository


class UploadSingleFile(
    private val repo: PostRepository
) {
    suspend operator fun invoke(
        fileUri: String
    ) = repo.uploadSingleFile(fileUri)
}