package com.kito.tlubook.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    @Exclude
    var id: String = "",
    var caption: String = "",
    var linkImage: String = "",
    var linkVideo: String = "",
    var createAt: Long = 0,
    var listUserLike: ArrayList<String> = arrayListOf(),
//    var listComment: ArrayList<Comment> = arrayListOf(),
    var likeCount: Int = 0,
    var commentCount: Int = 0,
    var userId: String = "",
) : Parcelable
