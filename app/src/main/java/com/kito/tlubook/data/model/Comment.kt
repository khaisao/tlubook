package com.kito.tlubook.data.model

data class Comment(
    var id: String = "",
    var content: String = "",
    var userId: String = "",
    var createAt: Long = 0
)
