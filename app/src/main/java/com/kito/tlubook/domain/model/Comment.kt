package com.kito.tlubook.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    @Exclude
    var id: String = "",
    var postId: String = "",
    var content: String = "",
    val createAt: Long = 0
) : Parcelable