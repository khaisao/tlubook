package com.kito.tlubook.data.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @Exclude
    var id:String="",
    var userName:String="",
    var email:String="",
    var password:String=""
):Parcelable
