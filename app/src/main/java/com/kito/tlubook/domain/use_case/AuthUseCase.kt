package com.kito.tlubook.domain.use_case

import com.kito.tlubook.domain.use_case.auth.GetSnapshotUser
import com.kito.tlubook.domain.use_case.post.*

data class AuthUseCase(
    val getSnapshotUser: GetSnapshotUser
)