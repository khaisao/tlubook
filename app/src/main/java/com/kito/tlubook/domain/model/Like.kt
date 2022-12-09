package com.kito.tlubook.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Like(
    @Exclude
    var id: String = "",
    var postId: String = "",
    var userId:String =""
) : Parcelable